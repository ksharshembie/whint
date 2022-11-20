package com.ksharshembie.whint.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.ksharshembie.whint.local.room.Stock

@Dao
interface StockDao {
    @Insert
    fun insert(stock: Stock)
}