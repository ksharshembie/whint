package com.ksharshembie.whint

import android.app.Application
import androidx.room.Room
import com.ksharshembie.whint.local.db.Database


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            Database::class.java, "database"
        ).allowMainThreadQueries().build()
    }

    companion object {
        lateinit var db: Database
    }
}