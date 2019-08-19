package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight

/**
 * Created by Belema Ogan on 2019-08-19.
 */
class SleepNightItemViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val sleepLength: TextView = itemView.findViewById(R.id.sleep_length)
    private val sleepQuality: TextView = itemView.findViewById(R.id.quality_string)
    private val qualityImageView: ImageView = itemView.findViewById(R.id.quality_image)

    fun bind(item: SleepNight) {
        val resource = itemView.context.resources
        sleepLength.text = convertDurationToFormatted(item.startTimeMilli,
                item.endTimeMilli, resource)
        sleepQuality.text = convertNumericQualityToString(item.sleepQuality, resource)
        qualityImageView.setImageResource(when (item.sleepQuality) {
            0 -> R.drawable.ic_sleep_0
            1 -> R.drawable.ic_sleep_1
            2 -> R.drawable.ic_sleep_2
            3 -> R.drawable.ic_sleep_3
            4 -> R.drawable.ic_sleep_4
            5 -> R.drawable.ic_sleep_5
            else -> R.drawable.ic_sleep_active
        })
    }

    companion object {
        fun from(parent: ViewGroup): SleepNightItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater
                    .inflate(R.layout.list_item_sleep_night, parent, false)
            return SleepNightItemViewHolder(view)
        }
    }
}