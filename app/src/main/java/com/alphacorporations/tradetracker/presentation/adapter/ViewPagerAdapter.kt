package com.alphacorporations.tradetracker.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alphacorporations.tradetracker.presentation.activity.fragment.StatsFragment
import com.alphacorporations.tradetracker.presentation.fragment.TradeFragment

/**
 * Created by Julien Jennequin on 01/11/2022 15:31
 * Project : TradeTracker
 **/
class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private val COUNT = 2

    override fun getItemCount(): Int {
        return COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TradeFragment()
            1 -> StatsFragment()
            else -> TradeFragment()
        }
    }
}
