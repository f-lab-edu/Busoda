package com.chaeny.busoda.stopdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chaeny.busoda.stopdetail.databinding.ListItemBusBinding

internal class StopDetailAdapter : ListAdapter<Bus, StopDetailAdapter.BusDetailViewHolder>(BusDetailDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusDetailViewHolder {
        return BusDetailViewHolder(
            ListItemBusBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BusDetailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BusDetailViewHolder(private val binding: ListItemBusBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(busData: Bus) {
            with(binding) {
                textBusNumber.text = busData.busNumber
                textNextStop.text = busData.nextStopName
            }
            bindArrivalInfos(busData, binding)
        }

        private fun bindArrivalInfos(busData: Bus, binding: ListItemBusBinding) {
            with(binding) {
                firstArrivalInfoView.bindArrivalInfo(busData.arrivalInfos.getOrNull(0), 0)
                secondArrivalInfoView.bindArrivalInfo(busData.arrivalInfos.getOrNull(1), 1)
            }
        }
    }
}

private class BusDetailDiffCallback : DiffUtil.ItemCallback<Bus>() {
    override fun areItemsTheSame(oldItem: Bus, newItem: Bus): Boolean {
        return oldItem.busNumber == newItem.busNumber
    }

    override fun areContentsTheSame(oldItem: Bus, newItem: Bus): Boolean {
        return oldItem == newItem
    }
}
