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

    @SerializedName("cmc_rank")
    val cmcRank: Long? = null,

    @SerializedName("num_market_pairs")
    val numMarketPairs: Long,

    @SerializedName("circulating_supply")
    val circulatingSupply: Long,

    @SerializedName("total_supply")
    val totalSupply: Long,

    @SerializedName("max_supply")
    val maxSupply: Long,

    @SerializedName("last_updated")
    val lastUpdated: String,

    @SerializedName("date_added")
    val dateAdded: String,

    val tags: List<String>,
    val platform: Any? = null,
    val quote: Map<String, Quote>
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
