package com.alphacorporations.tradetracker.data.websocket

/**
 * Created by Julien Jennequin on 08/12/2022 12:31
 * Project : TradeTracker
 **/
interface MessageListener {
    fun onConnectSuccess() // successfully connected
    fun onConnectFailed() // connection failed
    fun onClose() // close
    fun onMessage(text: String?)
}