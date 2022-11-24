package com.ksharshembie.whint.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ksharshembie.whint.local.room.Stock

@Dao
interface StockDao {
    @Insert
    fun insert(stock: List<Stock>)

    @Query("SELECT * from stock where isSystemStock = 1")
    fun isSystemStockExist(): Boolean

    @Query("select * from stock where isSystemStock = 1")
    fun getSystemStock(): List<Stock>

    @Query("select name from stock ")
    fun getStockNames(): List<String>

    @Query("select idStock from stock where name = :name ")
    fun getStockID(name: String): Long

    @Query("select * from stock where idStock = :id ")
    fun getStockNamebyID(id: Long): Stock
}