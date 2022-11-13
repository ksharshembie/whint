package com.ksharshembie.whint.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.ksharshembie.whint.local.room.SlipItem

@Dao
interface SlipItemDao {
    @Insert
    fun insert(slipItem: SlipItem)
}