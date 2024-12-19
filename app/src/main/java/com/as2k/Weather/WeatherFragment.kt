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



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class WeatherFragment : Fragment() {

    private lateinit var weatherTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)

        weatherTextView = view.findViewById(R.id.weatherTextView)
        weatherTextView.text = "Weather data will appear here."

        return view
    }

    fun refreshData() {
        weatherTextView.text = "Updated weather data."
    }
}
