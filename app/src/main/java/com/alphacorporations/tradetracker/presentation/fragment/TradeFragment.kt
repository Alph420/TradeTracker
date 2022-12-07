package com.alphacorporations.tradetracker.presentation.activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alphacorporations.tradetracker.databinding.FragmentTradeBinding

/**
 * Created by Julien Jennequin on 01/11/2022 15:19
 * Project : TradeTracker
 **/
class TradeFragment : Fragment() {
    private var _binding: FragmentTradeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTradeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()

    }

    private fun initObserver() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}