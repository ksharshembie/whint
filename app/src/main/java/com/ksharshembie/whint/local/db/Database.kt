package com.ksharshembie.whint.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ksharshembie.whint.local.dao.*
import com.ksharshembie.whint.local.room.*

@Database(entities = [Article::class, Slip::class, SlipItem::class, Stock::class, SlipType::class], version = 9)
abstract class Database : RoomDatabase() {
    abstract fun dao(): ArticleDao
    abstract fun daoSlip(): SlipDao
    abstract fun daoSlipItem(): SlipItemDao
    abstract fun daoSlipType(): SlipTypeDao
    abstract fun daoStock(): StockDao
}