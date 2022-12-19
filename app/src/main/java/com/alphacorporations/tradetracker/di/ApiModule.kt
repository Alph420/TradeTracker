package com.alphacorporations.tradetracker.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.alphacorporations.tradetracker.data.repository.CmcRepoImpl
import com.alphacorporations.tradetracker.data.source.remote.CmcApiService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Julien Jennequin on 13/12/2022 13:01
 * Project : TradeTracker
 **/
@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Provides
    @Singleton
    fun providesRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pro-api.coinmarketcap.com/")
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val mCache = Cache(context.cacheDir, cacheSize)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .cache(mCache) // make your app offline-friendly without a database!
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .addInterceptor { chain ->
                var request = chain.request()
                /* If there is Internet, get the cache that was stored 5 seconds ago.
                 * If the cache is older than 5 seconds, then discard it,
                 * and indicate an error in fetching the response.
                 * The 'max-age' attribute is responsible for this behavior.
                 */
                request =
                    if (true) request.newBuilder() // make default to true till i figure out how to inject network status
                        .header("Cache-Control", "public, max-age=" + 5).build()
                    /*If there is no Internet, get the cache that was stored 7 days ago.
                     * If the cache is older than 7 days, then discard it,
                     * and indicate an error in fetching the response.
                     * The 'max-stale' attribute is responsible for this behavior.
                     * The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
                     */
                    else request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                chain.proceed(request)
            }
        return client.build()
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }


    @Provides
    @Singleton
    fun provideIsNetworkAvailable(@ApplicationContext context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): CmcApiService {
        return retrofit.create(CmcApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAlbumRepository(
        retrofitService: CmcApiService
    ): CmcRepoImpl {
        return CmcRepoImpl(retrofitService)
    }


    //EXAMPLE
    /* @Singleton
     @Provides
     fun providePhotoRepository(
         appDatabase: AppDatabase,
         retrofitService: RetrofitService
     ): PhotoRepository {
         return PhotoRepositoryImp(appDatabase, retrofitService)
     }*/
}