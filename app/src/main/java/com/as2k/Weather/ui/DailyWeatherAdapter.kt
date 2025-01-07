package com.as2k.Weather.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.as2k.Weather.R
import com.as2k.Weather.model.DayWeather
import com.bumptech.glide.Glide

class DailyWeatherAdapter(private var days: List<DayWeather>) :
    RecyclerView.Adapter<DailyWeatherAdapter.DailyWeatherViewHolder>() {

    class DailyWeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tempText: TextView = itemView.findViewById(R.id.tempText)
        val descText: TextView = itemView.findViewById(R.id.descText)
        val weatherIcon: ImageView = itemView.findViewById(R.id.weatherIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_daily_weather, parent, false)
        return DailyWeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: DailyWeatherViewHolder, position: Int) {
        val day = days[position]
        holder.tempText.text = "${day.temp.min}°C - ${day.temp.max}°C"
        holder.descText.text = day.weather[0].description

        Glide.with(holder.itemView.context)
            .load("https://openweathermap.org/img/wn/${day.weather[0].icon}.png")
            .into(holder.weatherIcon)
    }

    override fun getItemCount() = days.size

    // Method to update data
    fun updateData(newDays: List<DayWeather>) {
        days = newDays
        notifyDataSetChanged()
    }
}
