package com.example.android.trackmysleepquality.sleeptracker

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by Belema Ogan on 2019-08-19.
 */
class SleepNightDiffCallback : DiffUtil.ItemCallback<DataItem>() {

    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }

}