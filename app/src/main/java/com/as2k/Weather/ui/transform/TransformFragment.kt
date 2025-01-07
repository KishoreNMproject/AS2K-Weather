package com.as2k.Weather.ui.transform

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.as2k.Weather.R
import com.as2k.Weather.network.RetrofitClient.weatherApi
import com.as2k.Weather.ui.DailyWeatherAdapter
import kotlinx.coroutines.launch


/**
 * Fragment that demonstrates a responsive layout pattern where the format of the content
 * transforms depending on the size of the screen. Specifically this Fragment shows items in
 * the [RecyclerView] using LinearLayoutManager in a small screen
 * and shows items using GridLayoutManager in a large screen.
 */
class TransformFragment : Fragment() {

    private lateinit var dailyWeatherAdapter: DailyWeatherAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.dailyWeatherRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize with empty data
        dailyWeatherAdapter = DailyWeatherAdapter(emptyList())
        recyclerView.adapter = dailyWeatherAdapter

        // Fetch data and update adapter
       fetchDailyWeather()
    }
    private fun fetchDailyWeather(){
        lifecycleScope.launch {
           try{
               val response = weatherApi.getDailyWeather("Chennai",7,"3ba4db5e39eeaf11da6e00d62ecbc034")
               if (response.isSuccessful){
                   response.body?.let {
                       dailyWeatherAdapter.updateData(it.list)
                   }
               }
            } catch (e: Exception){
                //Handle error
                e.printStackTrace()
            }
        }
    }
}

