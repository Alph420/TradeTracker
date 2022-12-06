package com.alphacorporations.tradetracker.domain.model

/**
 * Created by Julien Jennequin on 01/11/2022 16:02
 * Project : TradeTracker
 **/
data class Trade(
    val date: String,
    val biais: BiaisEnum,
    val ratio: Float,
    val description: String,
    val lotSize: Float,
    val pe: Float,
    val sl: Float,
    val tp:Float,
    //val tp: List<Float>
)

enum class BiaisEnum {
    Bull, Bear
}


