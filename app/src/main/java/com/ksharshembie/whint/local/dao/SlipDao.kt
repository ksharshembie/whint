package com.ksharshembie.whint.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.ksharshembie.whint.local.room.Slip

@Dao
interface SlipDao {
    @Insert
    fun insert(slip: Slip)
}