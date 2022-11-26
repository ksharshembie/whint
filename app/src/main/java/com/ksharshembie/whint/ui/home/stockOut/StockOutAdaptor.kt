package com.ksharshembie.whint.ui.home.stockOut

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ksharshembie.whint.App
import com.ksharshembie.whint.databinding.ItemStockOutBinding
import com.ksharshembie.whint.local.room.Article
import com.ksharshembie.whint.local.room.StockCount

class StockOutAdaptor() : Adapter<StockOutAdaptor.StockOutViewHolder>() {

    private val data = ArrayList<StockCount>()
    private lateinit var article: Article

    inner class StockOutViewHolder(private var binding: ItemStockOutBinding) :
        ViewHolder(binding.root) {
        fun bind(stockCount: StockCount, article: Article){
            binding.apply {
                tvArticleName.text = article.articleName
                tvArticleCode.text = article.articleCode
                tvQuantityStock.text = stockCount.quantity.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockOutViewHolder {
        return StockOutViewHolder(
            ItemStockOutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StockOutViewHolder, position: Int) {
        article = App.db.dao().getArticlebyID(data[position].idArticle)
        holder.bind(data[position],article)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addItem(list: List<StockCount>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }
}