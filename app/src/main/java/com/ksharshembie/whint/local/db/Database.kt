package com.ksharshembie.whint.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ksharshembie.whint.local.dao.ArticleDao
import com.ksharshembie.whint.local.dao.SlipDao
import com.ksharshembie.whint.local.dao.SlipItemDao
import com.ksharshembie.whint.local.room.Article
import com.ksharshembie.whint.local.room.Slip
import com.ksharshembie.whint.local.room.SlipItem

@Database(entities = [Article::class, Slip::class, SlipItem::class], version = 2)
abstract class Database : RoomDatabase() {
    abstract fun dao(): ArticleDao
    abstract fun daoSlip(): SlipDao
    abstract fun daoSlipItem(): SlipItemDao
}