/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.database.SleepNight
import kotlinx.coroutines.*
import java.lang.ClassCastException

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_ITEM = 1

class SleepNightAdapter(private val clickListener: SleepNightItemClickListener) :
        ListAdapter<DataItem, RecyclerView.ViewHolder>(SleepNightDiffCallback()){

    private val parentJob = Job()

    private val adapterScope = CoroutineScope(Dispatchers.Default + parentJob)

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.SleepNightItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> SleepNightItemViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is SleepNightItemViewHolder -> {
                val sleepNightItem = getItem(position) as DataItem.SleepNightItem
                holder.bind(sleepNightItem.sleepNight, clickListener)
            }
        }
    }

    fun addHeaderAndSubmitList(list: List<SleepNight>?){
        adapterScope.launch {
            val items = when(list){
                null -> listOf(DataItem.Header)
                //add header to list and return list of DataItems
                else -> listOf(DataItem.Header) + list.map { DataItem.SleepNightItem(it) }
            }

            withContext(Dispatchers.Main){
                submitList(items)
            }
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        parentJob.cancel()
        super.onDetachedFromRecyclerView(recyclerView)
    }

}
