package com.ksharshembie.whint.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StockCount(
    @PrimaryKey(autoGenerate = true)
    var idStocks: Long? = null,
    var idArticle: Long,
    var idStock: Long,
    var quantity: Int
)
