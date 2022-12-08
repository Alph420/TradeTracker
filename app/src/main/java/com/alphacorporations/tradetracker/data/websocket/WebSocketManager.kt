package com.alphacorporations.tradetracker.data.websocket

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okio.ByteString
import java.util.concurrent.TimeUnit

/**
 * Created by Julien Jennequin on 08/12/2022 12:34
 * Project : TradeTracker
 **/
object WebSocketManager {
    private val TAG = WebSocketManager::class.java.simpleName
    private val MAX_NUM = 5  // Maximum number of reconnections
    private val MILLIS = 5000  // Reconnection interval, milliseconds
    private lateinit var client: OkHttpClient
    private lateinit var request: Request
    private lateinit var messageListener: MessageListener
    private lateinit var mWebSocket: WebSocket
    private var isConnect = false
    private var connectNum = 0

    fun init(url: String, _messageListener: MessageListener) {
        var client = OkHttpClient.Builder()
            .writeTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
        var request = Request.Builder().url(url).build()
        var messageListener = _messageListener
    }

    fun connect() {
        if (isConnect) {
            Log.i(TAG, "web socket connected")
            return
        }
        //client.newWebSocket(request, createListener())
    }

    fun reconnect() {
        if (connectNum <= MAX_NUM) {
            try {
                Thread.sleep(MILLIS.toLong())
                connect()
                connectNum++
            } catch (e: InterruptedException) {
                e.printStackTrace ()
            }
        } else {
            Log.i(
                TAG,
                "reconnect over $MAX_NUM,please check url or network"
            )
        }
    }

    fun sendMessage(text: String): Boolean {
        return if (!isConnect) false else mWebSocket.send(text)
    }
    fun sendMessage(byteString: ByteString): Boolean {
        return if (!isConnect) false else mWebSocket.send(byteString)
    }

    fun close() {
        if (isConnect) {
            mWebSocket.cancel()
            mWebSocket.close( 1001 , "The client actively closes the connection " )
        }
    }
}