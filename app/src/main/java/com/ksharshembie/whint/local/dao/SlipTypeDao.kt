package com.ksharshembie.whint.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.ksharshembie.whint.local.room.SlipType

@Dao
interface SlipTypeDao {

    @Insert
    fun insert(slipType: SlipType)
}