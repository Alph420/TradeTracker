package com.alphacorporations.tradetracker.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alphacorporations.tradetracker.databinding.ActivityMainBinding
import com.alphacorporations.tradetracker.databinding.CreateTradeActivityBinding

/**
 * Created by Julien Jennequin on 12/11/2022 20:53
 * Project : TradeTracker
 **/
class CreateTradeActivity  : AppCompatActivity() {

    private lateinit var binding: CreateTradeActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CreateTradeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListener()

    }

    private fun initListener(){

        binding.saveBtn.setOnClickListener {
            //TODO CHECK DATA AND SAVED IT
            finish()
        }
    }

    override fun onBackPressed() {
        finish()
    }
}