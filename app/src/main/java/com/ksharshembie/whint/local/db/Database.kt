package com.ksharshembie.whint.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ksharshembie.whint.local.dao.ArticleDao
import com.ksharshembie.whint.local.room.Article

@Database(entities = [Article::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun dao():ArticleDao
}