package com.alphacorporations.tradetracker.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alphacorporations.tradetracker.domain.model.Trade
import com.alphacorporations.tradetracker.domain.repository.CoinMarketCapRepositoryImpl
import com.alphacorporations.tradetracker.domain.repository.DatabaseRepositoryImpl
import com.alphacorporations.tradetracker.utils.Converter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTradeViewModel @Inject constructor(
    private val dbRepo: DatabaseRepositoryImpl,
    private val cmcRepo: CoinMarketCapRepositoryImpl

) :
    ViewModel() {

    fun saveTrade(trade: Trade) {
        viewModelScope.launch(Dispatchers.IO) {
            dbRepo.insert(Converter.fromTradeToTradeEntity(trade))
        }
    }

    fun getCryptoList() {
        viewModelScope.launch(Dispatchers.IO) {
            var test = cmcRepo.getHistoricalListings()
            Log.d(this.javaClass.name, test.toString())
        }
    }


}