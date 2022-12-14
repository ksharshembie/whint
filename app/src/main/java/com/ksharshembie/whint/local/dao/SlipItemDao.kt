package com.ksharshembie.whint.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ksharshembie.whint.local.room.SlipItem

@Dao
interface SlipItemDao {
    @Insert
    fun insert(slipItem: SlipItem)

    @Query("Select * from slipitem where idSlip = :slipId")
    fun getSlipItems(slipId: Long):List<SlipItem>

    @Query("DELETE FROM slipitem where idSlip =:slipID")
    fun deleteSlipItem(slipID: Long)

    @Query("SELECT EXISTS(SELECT * FROM slipitem where idSlip = :slipID)")
    fun isSlipItemExist(slipID: Long): Boolean
}