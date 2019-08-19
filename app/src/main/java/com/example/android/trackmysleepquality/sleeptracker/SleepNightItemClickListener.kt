package com.example.android.trackmysleepquality.sleeptracker

import com.example.android.trackmysleepquality.database.SleepNight

/**
 * Created by Belema Ogan on 2019-08-19.
 */
class SleepNightItemClickListener(val clickListener: (sleepNightId: Long) -> Unit) {
    fun onClick(sleepNight: SleepNight) = clickListener(sleepNight.nightId)
}