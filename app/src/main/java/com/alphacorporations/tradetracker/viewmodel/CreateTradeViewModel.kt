package com.alphacorporations.tradetracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alphacorporations.tradetracker.domain.model.Trade
import com.alphacorporations.tradetracker.domain.repository.TradeRepositoryImpl
import com.alphacorporations.tradetracker.utils.Converter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTradeViewModel @Inject constructor(private val repository: TradeRepositoryImpl) :
    ViewModel() {

    suspend fun getTrades() {
        return Converter.fromTradeEntityToTrade(repository.trades)
    }

    fun saveTrade(trade: Trade) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(Converter.fromTradeToTradeEntity(trade))
        }
    }


}