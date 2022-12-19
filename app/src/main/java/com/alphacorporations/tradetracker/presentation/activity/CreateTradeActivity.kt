package com.alphacorporations.tradetracker.presentation.activity

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.alphacorporations.tradetracker.R
import com.alphacorporations.tradetracker.databinding.CreateTradeActivityBinding
import com.alphacorporations.tradetracker.domain.model.BiaisEnum
import com.alphacorporations.tradetracker.domain.model.Trade
import com.alphacorporations.tradetracker.presentation.viewmodel.CreateTradeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

/**
 * Created by Julien Jennequin on 12/11/2022 20:53
 * Project : TradeTracker
 **/
@AndroidEntryPoint
class CreateTradeActivity : AppCompatActivity() {

    private lateinit var binding: CreateTradeActivityBinding
    private val viewModel: CreateTradeViewModel by viewModels()

    private var biais: BiaisEnum = BiaisEnum.Bear

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CreateTradeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListener()
        initObserver()

    }

    private fun initListener() {

        binding.saveBtn.setOnClickListener {
            if (verify()) {
                saveTrade()
                finish()
            } else {
                setTextFieldError()
            }


        }

        binding.slider.addOnChangeListener { slider, value, fromUser ->
            if (value in 1F..100F) {
                binding.leverageText.setText("${value.toInt()}")
            }
        }

        binding.biaisGroup.setOnCheckedChangeListener { group, checkId ->
            when (checkId) {
                R.id.bearish -> {
                    binding.leverageCardView.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(this, R.color.red_50))
                    biais = BiaisEnum.Bear
                }
                R.id.bullish -> {
                    binding.leverageCardView.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(this, R.color.green_50))
                    biais = BiaisEnum.Bull

                }
            }
            viewModel.verifyInputValue(
                binding.entryPrice.text.toString(),
                binding.stopLossPrice.text.toString(),
                binding.takeProfitPrice.text.toString(),
                biais
            )
        }

        binding.entryPrice.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.verifyInputValue(
                    binding.entryPrice.text.toString(),
                    binding.stopLossPrice.text.toString(),
                    binding.takeProfitPrice.text.toString(),
                    biais
                )
            }

            override fun onTextChanged(pe: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(pe: Editable?) {
                viewModel.verifyInputValue(
                    pe.toString(),
                    binding.stopLossPrice.text.toString(),
                    binding.takeProfitPrice.text.toString(),
                    biais
                )
            }

        })

        binding.stopLossPrice.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.verifyInputValue(
                    binding.entryPrice.text.toString(),
                    binding.stopLossPrice.text.toString(),
                    binding.takeProfitPrice.text.toString(),
                    biais
                )
            }

            override fun onTextChanged(pe: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(sl: Editable?) {
                viewModel.verifyInputValue(
                    binding.entryPrice.text.toString(),
                    sl.toString(),
                    binding.takeProfitPrice.text.toString(),
                    biais
                )
            }

        })

        binding.takeProfitPrice.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.verifyInputValue(
                    binding.entryPrice.text.toString(),
                    binding.stopLossPrice.text.toString(),
                    binding.takeProfitPrice.text.toString(),
                    biais
                )
            }

            override fun onTextChanged(pe: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(tp: Editable?) {
                viewModel.verifyInputValue(
                    binding.entryPrice.text.toString(),
                    binding.stopLossPrice.text.toString(),
                    tp.toString(),
                    biais
                )
            }

        })


        binding.leverageText.addTextChangedListener {
            if (binding.leverageText.text!!.isNotEmpty()) {
                if (it.toString().toInt() > 100) {
                    binding.leverageText.setText("100")
                }
                if (it.toString().toInt() < 1) {
                    binding.leverageText.setText("1")
                }
                if (it.toString().toFloat() in 1F..100F) {

                    binding.slider.value = it.toString().toFloat()
                }
            } else {
                binding.leverageText.setText("1")
            }
        }

    }

    private fun initObserver() {
        viewModel.getCryptoList()

        viewModel.entryPriceEditTextError.observe(this) {
            binding.entryPrice.error = it
        }

        viewModel.stopLossEditTextError.observe(this) {
            binding.stopLossPrice.error = it
        }

        viewModel.takeProfitEditTextError.observe(this) {
            binding.takeProfitPrice.error = it
        }

        viewModel.btnSaveState.observe(this) {
            binding.saveBtn.isEnabled = it
        }
    }

    private fun saveTrade() {
        viewModel.saveTrade(
            Trade(
                Date().time,
                binding.pairName.text.toString(),
                biais,
                0f,
                "",
                if (binding.lotSize.text.toString()
                        .isEmpty()
                ) 0f else binding.lotSize.text.toString().toFloat(),
                binding.entryPrice.text.toString().toFloat(),
                binding.stopLossPrice.text.toString().toFloat(),
                binding.takeProfitPrice.text.toString().toFloat(),
                binding.slider.value
            )
        )
    }

    private fun setTextFieldError() {
        if (binding.pairName.text!!.isEmpty()) {
            binding.pairName.error = "Cannot be empty"
        } else {
            binding.pairName.error = null
        }

        if (binding.entryPrice.text!!.isEmpty()) {
            binding.entryPrice.error = "Cannot be empty"
        } else {
            binding.entryPrice.error = null
        }

        if (binding.stopLossPrice.text!!.isEmpty()) {
            binding.stopLossPrice.error = "Cannot be empty"
        } else {
            binding.stopLossPrice.error = null
        }

        if (binding.takeProfitPrice.text!!.isEmpty()) {
            binding.takeProfitPrice.error = "Cannot be empty"
        } else {
            binding.takeProfitPrice.error = null
        }
    }

    private fun verify(): Boolean {
        return binding.pairName.toString()
            .isNotEmpty() && binding.entryPrice.text.toString()
            .isNotEmpty() && binding.stopLossPrice.text.toString()
            .isNotEmpty() && binding.takeProfitPrice.text.toString().isNotEmpty()
    }

    override fun onBackPressed() {
        finish()
    }
}
