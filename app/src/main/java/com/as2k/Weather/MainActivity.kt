package com.as2k.Weather

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.as2k.Weather.databinding.ActivityMainBinding
import com.ask.weather.WeatherViewModel
import com.google.android.material.snackbar.Snackbar
import androidx.viewbinding.ViewBinding

private val ViewBinding.temperatureTextView: TextView
    get() = root.findViewById(R.id.temperatureTextView)

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set Toolbar
        setSupportActionBar(binding.appBarMain.findViewById(R.id.toolbar))

        // Set FAB action
        binding.root.findViewById<View>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Refreshing Map data", Snackbar.LENGTH_LONG).show()
        }

        // Set up Navigation
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_transform, R.id.nav_reflow, R.id.nav_slideshow, R.id.nav_settings),
            binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        // Observe ViewModel
        viewModel.weather.observe(this, Observer { weather ->
            binding.temperatureTextView.text = "Temperature: ${weather.main.temp}°C"
        })

        // Fetch Weather Data
        viewModel.fetchWeather("Chennai", "3ba4db5e39eeaf11da6e00d62ecbc034")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.overflow, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.nav_settings) {
            val navController = findNavController(R.id.nav_host_fragment_content_main)
            navController.navigate(R.id.nav_settings)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
