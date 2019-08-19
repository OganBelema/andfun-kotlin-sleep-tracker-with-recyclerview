package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding

/**
 * Created by Belema Ogan on 2019-08-19.
 */
class SleepNightItemViewHolder private constructor(
        private val listItemSleepNightBinding: ListItemSleepNightBinding):
        RecyclerView.ViewHolder(listItemSleepNightBinding.root) {

    fun bind(sleepNight: SleepNight) {
        listItemSleepNightBinding.sleepNight = sleepNight
        listItemSleepNightBinding.executePendingBindings()

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