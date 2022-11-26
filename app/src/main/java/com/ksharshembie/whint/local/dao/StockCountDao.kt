package com.ksharshembie.whint.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ksharshembie.whint.local.room.StockCount

@Dao
interface StockCountDao {

    @Insert
    fun insert(stocks: StockCount)

    @Query("select exists(select * from stockcount where idArticle = :article and idStock = :store)")
    fun isStockExist(article: Long, store: Long): Boolean

    @Query("select * from stockcount where idArticle = :article and idStock = :store")
    fun existStock(article: Long, store: Long): StockCount

    @Query("Update stockcount set quantity = :newStock where idStocks = :idStock")
    fun inreaseStocks(idStock: Long? = null, newStock: Int)

    @Query("select * from stockcount order by idArticle, idStock")
    fun getAllStock(): List<StockCount>

    @Query("select * from stockcount where idStock = :stockID order by idArticle, idStock")
    fun getAllStockByID(stockID: Long): List<StockCount>
}
