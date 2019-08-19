package com.example.android.trackmysleepquality.sleeptracker

import com.example.android.trackmysleepquality.database.SleepNight

/**
 * Created by Belema Ogan on 2019-08-19.
 */
sealed class DataItem {

    abstract val id: Long

    data class SleepNightItem(val sleepNight: SleepNight): DataItem(){
        override val id = sleepNight.nightId
    }

    object Header: DataItem(){
        override val id = Long.MIN_VALUE
    }
}