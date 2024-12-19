package com.as2k.Weather

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.appcompat.app.AppCompatActivity
import com.as2k.Weather.databinding.ActivityMainBinding
import androidx.fragment.app.Fragment



import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var refreshButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize components
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
        refreshButton = findViewById(R.id.refreshButton)

        // Set up ViewPager with an adapter
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        // Attach TabLayout to ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Weather Info"
                1 -> tab.text = "Other Info"
                2 -> tab.text = "Settings"
            }
        }.attach()

        // Refresh button action
        refreshButton.setOnClickListener {
            refreshWeatherData()
        }
    }

    private fun refreshWeatherData() {
        Toast.makeText(this, "Refreshing weather data...", Toast.LENGTH_SHORT).show()

        // Update the WeatherFragment if loaded
        val fragment = supportFragmentManager.findFragmentByTag("f0")
        if (fragment is WeatherFragment) {
            fragment.refreshData()
        }
    }
}
