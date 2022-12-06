package com.alphacorporations.tradetracker.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.alphacorporations.tradetracker.databinding.CreateTradeActivityBinding
import com.alphacorporations.tradetracker.domain.model.BiaisEnum
import com.alphacorporations.tradetracker.domain.model.Trade
import com.alphacorporations.tradetracker.viewmodel.CreateTradeViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Julien Jennequin on 12/11/2022 20:53
 * Project : TradeTracker
 **/
@AndroidEntryPoint
class CreateTradeActivity : AppCompatActivity() {

    private lateinit var binding: CreateTradeActivityBinding
    private val viewModel: CreateTradeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CreateTradeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListener()


    }

    private fun initListener() {

        binding.saveBtn.setOnClickListener {
            //TODO CHECK DATA AND SAVED IT
            viewModel.saveTrade(
                Trade(
                    "", BiaisEnum.Bear, 1.2f, "", 1f, 1f, 1f, 1f
                )
            )
            finish()
        }
    }

    override fun onBackPressed() {
        finish()
    }
}