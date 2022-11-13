package com.ksharshembie.whint.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ksharshembie.whint.local.room.Slip

@Dao
interface SlipDao {
    @Insert
    fun insert(slip: Slip)

    @Query("SELECT idSlip FROM slip WHERE idDate = :date")
    fun getID(date: Long): Long
}