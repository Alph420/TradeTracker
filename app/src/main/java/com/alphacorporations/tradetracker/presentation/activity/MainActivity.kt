package com.alphacorporations.tradetracker.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.ui.AppBarConfiguration
import com.alphacorporations.tradetracker.R
import com.alphacorporations.tradetracker.adapter.ViewPagerAdapter
import com.alphacorporations.tradetracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewPager()
        initListener()
    }

    private fun initViewPager() {
        binding.pager.adapter = ViewPagerAdapter(this)
    }

    private fun initListener() {
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