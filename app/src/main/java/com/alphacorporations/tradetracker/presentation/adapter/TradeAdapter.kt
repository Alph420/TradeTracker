package com.alphacorporations.tradetracker.presentation.adapter

import android.content.res.ColorStateList
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alphacorporations.tradetracker.R
import com.alphacorporations.tradetracker.databinding.TradeItemBinding
import com.alphacorporations.tradetracker.domain.model.BiaisEnum
import com.alphacorporations.tradetracker.domain.model.Trade
import java.util.*

/**
 * Created by Julien Jennequin on 07/12/2022 14:55
 * Project : TradeTracker
 **/
class TradeAdapter : RecyclerView.Adapter<TradeAdapter.TradeViewHolder>() {
    private var dataList: MutableList<Trade> = mutableListOf()

    class TradeViewHolder(val binding: TradeItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TradeViewHolder {
        return TradeViewHolder(
            TradeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TradeViewHolder, position: Int) {
        val binding = holder.binding
        val trade = dataList[position]

        binding.pairName.text = trade.pair
        binding.Biais.text = if (trade.biais == BiaisEnum.Bull) "Buy (long)" else "Sell (short)"
        binding.qty.text = trade.lotSize.toString()
        binding.date.text = DateFormat.format("MM-dd-yyyy HH:mm", Date(trade.date)).toString()
        binding.takeProfitPrice.text = trade.tp.toString()
        binding.entryPrice.text = trade.pe.toString()
        binding.pairName.text = trade.pair

        when (trade.biais) {
            BiaisEnum.Bear -> {
                binding.Biais.setTextColor(
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.red_80
                        )
                    )
                )
                binding.qty.setTextColor(
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.red_80
                        )
                    )
                )
            }
            BiaisEnum.Bull -> {
                binding.Biais.setTextColor(
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.green_80
                        )
                    )
                )
                binding.qty.setTextColor(
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.green_80
                        )
                    )
                )
            }
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setDataList(trades: List<Trade>) {
        this.clear()
        this.dataList.addAll(trades)
        notifyDataSetChanged()
    }

    fun clear() {
        dataList.clear()
        notifyDataSetChanged()
    }

    fun isEmpty(): Boolean {
        return dataList.isEmpty()
    }
}
