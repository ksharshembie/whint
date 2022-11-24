package com.ksharshembie.whint.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ksharshembie.whint.App
import com.ksharshembie.whint.databinding.ItemHomeStockBinding
import com.ksharshembie.whint.local.room.Article
import com.ksharshembie.whint.local.room.Stock
import com.ksharshembie.whint.local.room.StockCount

class StockAdaptor : Adapter<StockAdaptor.StockViewHolder>() {
    private val data = ArrayList<StockCount>()
    private lateinit var article: Article
    private lateinit var store: Stock


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        return StockViewHolder(
            ItemHomeStockBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        article = App.db.dao().getArticlebyID(data[position].idArticle)
        store = App.db.daoStock().getStockNamebyID(data[position].idStock)
        holder.bind(data[position],article, store)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addItem(list: List<StockCount>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    inner class StockViewHolder(private var binding: ItemHomeStockBinding) :
        ViewHolder(binding.root) {
        fun bind(stockCount: StockCount, article: Article, stock: Stock) {
            binding.apply {
                tvArticleName.text = article.articleName
                tvArticleCode.text = article.articleCode
                tvQuantity.text = stockCount.quantity.toString()
                tvStoreName.text = stock.name
            }
        }
    }
}