package com.ksharshembie.whint.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ksharshembie.whint.databinding.ItemStoreBinding
import com.ksharshembie.whint.local.room.Stock


class StoreAdaptor : RecyclerView.Adapter<StoreAdaptor.StoreViewHolder>() {

    private val data = ArrayList<Stock>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        return StoreViewHolder(
            ItemStoreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addStores(list: List<Stock>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    inner class StoreViewHolder(private var binding: ItemStoreBinding) : ViewHolder(binding.root) {
        fun bind(stock: Stock) {

        }
    }
}