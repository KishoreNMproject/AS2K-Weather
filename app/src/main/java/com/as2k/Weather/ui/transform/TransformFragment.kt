package com.as2k.Weather.ui.transform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.as2k.Weather.R
import com.as2k.Weather.databinding.FragmentTransformBinding


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
        lifecycleScope.launch {
            val response = weatherApi.getDailyWeather("London", 7, "your_api_key")
            if (response.isSuccessful) {
                response.body()?.let {
                    dailyWeatherAdapter.updateData(it.list)
                }
            }
        }
    }
}
