package com.alphacorporations.tradetracker.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.ui.AppBarConfiguration
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.alphacorporations.tradetracker.R
import com.alphacorporations.tradetracker.databinding.ActivityMainBinding
import com.alphacorporations.tradetracker.presentation.adapter.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val sandboxUrl = "wss://ws-sandbox.coinapi.io/v1/"
    private val noSandboxUrl = "wss://ws.coinapi.io/v1/"

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initWebSocket()
        initViewPager()
        initListener()
    }

    private fun initWebSocket() {
    }

    private fun initViewPager() {
        binding.pager.adapter = ViewPagerAdapter(this)
    }

    private fun initListener() {
        binding.pager.registerOnPageChangeCallback(object : OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        binding.bottomNavigationView.selectedItemId = R.id.test_1
                    }
                    1 -> {
                        binding.bottomNavigationView.selectedItemId = R.id.test_2
                    }
                }
            }

        })
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.test_1 -> {
                    binding.pager.currentItem = 0
                    true
                }
                R.id.test_2 -> {
                    binding.pager.currentItem = 1
                    true
                }
                else -> {
                    binding.pager.currentItem = 0
                    true
                }
            }
        }
        binding.fab.setOnClickListener {
            startActivity(Intent(this, CreateTradeActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

}