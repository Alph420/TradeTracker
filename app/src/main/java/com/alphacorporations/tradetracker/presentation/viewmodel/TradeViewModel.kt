package com.alphacorporations.tradetracker.presentation.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alphacorporations.tradetracker.data.repository.CmcRepoImpl
import com.alphacorporations.tradetracker.domain.model.Trade
import com.alphacorporations.tradetracker.utils.Converter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Julien Jennequin on 07/12/2022 13:29
 * Project : TradeTracker
 **/
@HiltViewModel
class TradeViewModel @Inject constructor(
    private val cmcRepo: CmcRepoImpl
) : ViewModel() {

    val tradeListLiveData: MutableLiveData<List<Trade>> by lazy {
        MutableLiveData<List<Trade>>()
    }

    fun getTrades() {
        viewModelScope.launch(Dispatchers.IO) {
            tradeListLiveData.postValue(Converter.fromTradeEntityToTrade(cmcRepo.getAllTrade()))
        }
    }

    fun getHistorical() {
        viewModelScope.launch(Dispatchers.IO) {
            cmcRepo.getHistoricalListings()
        }

    }


}