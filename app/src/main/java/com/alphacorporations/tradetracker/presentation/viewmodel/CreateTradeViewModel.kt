package com.alphacorporations.tradetracker.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alphacorporations.tradetracker.data.repository.DatabaseRepoImpl
import com.alphacorporations.tradetracker.domain.model.BiaisEnum
import com.alphacorporations.tradetracker.domain.model.Trade
import com.alphacorporations.tradetracker.utils.Converter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTradeViewModel @Inject constructor(
    private val dbRepo: DatabaseRepoImpl
) : ViewModel() {

    val entryPriceEditTextError: MutableLiveData<String?> = MutableLiveData(null)
    val stopLossEditTextError: MutableLiveData<String?> = MutableLiveData(null)
    val takeProfitEditTextError: MutableLiveData<String?> = MutableLiveData(null)
    val btnSaveState: MutableLiveData<Boolean> = MutableLiveData(false)

    fun saveTrade(trade: Trade) {
        viewModelScope.launch(Dispatchers.IO) {
            dbRepo.insert(Converter.fromTradeToTradeEntity(trade))
        }
    }

    fun getCryptoList() {
        viewModelScope.launch(Dispatchers.IO) {
            cmcApiRepo.getHistoricalListings()
        }
    }

    fun verifyInputValue(pe: String, sl: String, tp: String, biais: BiaisEnum) {
        if (pe.isNotEmpty() && sl.isNotEmpty() && tp.isNotEmpty()) {

            if (biais == BiaisEnum.Bull) {
                if (pe.toFloat() < sl.toFloat()) {
                    entryPriceEditTextError.postValue("Entry price cannot be lower than Stop Loss")
                    stopLossEditTextError.postValue("Stop Loss cannot be greater than entry price")
                    btnSaveState.postValue(false)

                } else {
                    entryPriceEditTextError.postValue(null)
                    stopLossEditTextError.postValue(null)
                    btnSaveState.postValue(true)

                }

                if (tp.toFloat() < pe.toFloat()) {
                    entryPriceEditTextError.postValue("Entry price cannot be greater than Take profit")
                    takeProfitEditTextError.postValue("Take profit cannot be lower than entry price")
                    btnSaveState.postValue(false)


                } else {
                    entryPriceEditTextError.postValue(null)
                    takeProfitEditTextError.postValue(null)
                    btnSaveState.postValue(true)

                }
            } else {
                if (pe.toFloat() > sl.toFloat()) {
                    entryPriceEditTextError.postValue("Entry price cannot be greater than Stop Loss")
                    stopLossEditTextError.postValue("Stop loss cannot be lower than entry price")
                    btnSaveState.postValue(false)

                } else {
                    entryPriceEditTextError.postValue(null)
                    stopLossEditTextError.postValue(null)
                    btnSaveState.postValue(true)

                }

                if (tp.toFloat() > pe.toFloat()) {
                    entryPriceEditTextError.postValue("Entry price cannot be lower than Take profit")
                    takeProfitEditTextError.postValue("Take profit cannot be greater than entry price")
                    btnSaveState.postValue(false)

                } else {
                    entryPriceEditTextError.postValue(null)
                    takeProfitEditTextError.postValue(null)
                    btnSaveState.postValue(true)

                }
            }
        } else {
            if (pe.isEmpty()) {
                entryPriceEditTextError.postValue("This field cannot be empty")
            }
            if (sl.isEmpty()) {
                stopLossEditTextError.postValue("This field cannot be empty")
            }
            if (tp.isEmpty()) {
                takeProfitEditTextError.postValue("This field cannot be empty")
            }
            btnSaveState.postValue(false)
        }
    }

}