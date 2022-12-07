package com.alphacorporations.tradetracker.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alphacorporations.tradetracker.databinding.FragmentTradeBinding
import com.alphacorporations.tradetracker.presentation.adapter.TradeAdapter
import com.alphacorporations.tradetracker.viewmodel.TradeViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Julien Jennequin on 01/11/2022 15:19
 * Project : TradeTracker
 **/
@AndroidEntryPoint
class TradeFragment : Fragment() {
    private var _binding: FragmentTradeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TradeViewModel by viewModels()

    lateinit var adapter: TradeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTradeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initListener()
        initObserver()

    }

    private fun initAdapter() {
        adapter = TradeAdapter()
        binding.recycler.adapter = adapter
    }

    private fun initListener() {
        binding.pull.setOnRefreshListener {
            viewModel.getTrades()
            binding.pull.isRefreshing = false
        }
    }

    private fun initObserver() {
        viewModel.tradeListLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setDataList(it)
            }

        }
        viewModel.getTrades()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTrades()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}