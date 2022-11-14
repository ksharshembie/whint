package com.ksharshembie.whint.ui.home.stockIn

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ksharshembie.whint.databinding.ItemStockInBinding
import com.ksharshembie.whint.local.room.SlipItem

class StockInAdapter() : RecyclerView.Adapter<StockInAdapter.StockInViewHolder>() {
    private val data = ArrayList<SlipItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockInViewHolder {
        return StockInViewHolder(
            ItemStockInBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StockInViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addItem(list: List<SlipItem>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    inner class StockInViewHolder(private var binding: ItemStockInBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(slipItem: SlipItem) {
            binding.apply {
                tvArticleCode.text = slipItem.articleCode
                tvArticleName.text = slipItem.articleName
                tvQuantity.text = slipItem.quantity.toString()
                tvArticlePrice.text = slipItem.price.toString()
            }
        }

    }

}