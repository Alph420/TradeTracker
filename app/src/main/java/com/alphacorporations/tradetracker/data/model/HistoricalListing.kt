package com.alphacorporations.tradetracker.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Julien Jennequin on 08/12/2022 15:46
 * Project : TradeTracker
 **/
data class HistoricalListing(
    val data: List<Coin>,
    val status: Status
)

data class Coin(
    val id: Long,
    val name: String,
    val symbol: String,
    val slug: String,

    @SerializedName("num_market_pairs")
    val numMarketPairs: Long,

    @SerializedName("date_added")
    val dateAdded: String,

    val tags: List<String>,

    @SerializedName("max_supply")
    val maxSupply: Long? = null,

    @SerializedName("circulating_supply")
    val circulatingSupply: Double,

    @SerializedName("total_supply")
    val totalSupply: Double,

    val platform: Platform? = null,

    @SerializedName("cmc_rank")
    val cmcRank: Long,

    @SerializedName("self_reported_circulating_supply")
    val selfReportedCirculatingSupply: Double? = null,

    @SerializedName("self_reported_market_cap")
    val selfReportedMarketCap: Double? = null,

    @SerializedName("tvl_ratio")
    val tvlRatio: Double? = null,

    @SerializedName("last_updated")
    val lastUpdated: String,

    val quote: Quote
)

data class Platform(
    val id: Long,
    val name: String,
    val symbol: String,
    val slug: String,

    @SerializedName("token_address")
    val tokenAddress: String
)

data class Quote(
    val price: Double,

    @SerializedName("volume_24h")
    val volume24H: Long,

    @SerializedName("percent_change_1h")
    val percentChange1H: Double,

    @SerializedName("percent_change_24h")
    val percentChange24H: Double,

    @SerializedName("percent_change_7d")
    val percentChange7D: Double,

    @SerializedName("market_cap")
    val marketCap: Long,

    @SerializedName("last_updated")
    val lastUpdated: String
)

data class Status(
    val timestamp: String,

    @SerializedName("error_code")
    val errorCode: Long,

    @SerializedName("error_message")
    val errorMessage: String,

    val elapsed: Long,

    @SerializedName("credit_count")
    val creditCount: Long
)
