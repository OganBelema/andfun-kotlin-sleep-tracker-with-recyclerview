package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding

/**
 * Created by Belema Ogan on 2019-08-19.
 */
class SleepNightItemViewHolder private constructor(
        private val listItemSleepNightBinding: ListItemSleepNightBinding):
        RecyclerView.ViewHolder(listItemSleepNightBinding.root) {

    fun bind(item: SleepNight) {
        val resource = itemView.context.resources
        listItemSleepNightBinding.sleepLength.text = convertDurationToFormatted(item.startTimeMilli,
                item.endTimeMilli, resource)
        listItemSleepNightBinding.qualityString.text = convertNumericQualityToString(
                item.sleepQuality, resource)
        listItemSleepNightBinding.qualityImage.setImageResource(when (item.sleepQuality) {
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
            val listItemSleepNightBinding = ListItemSleepNightBinding.inflate(layoutInflater,
                    parent, false)
            return SleepNightItemViewHolder(listItemSleepNightBinding)
        }
    }
}