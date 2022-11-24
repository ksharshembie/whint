package com.ksharshembie.whint.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ksharshembie.whint.local.room.Slip

@Dao
interface SlipDao {
    @Insert
    fun insert(slip: Slip)

    @Query("SELECT * FROM slip WHERE idDate = :slipID")
    fun getSlipByID(slipID: Long): Slip

    @Query("SELECT idSlip FROM slip WHERE idDate = :date")
    fun getID(date: Long): Long

    @Query("DELETE FROM slip where idSlip =:slipID")
    fun deleteSlip(slipID: Long)

    @Query("SELECT EXISTS(SELECT * FROM slip where idSlip = :slipID)")
    fun isSlipExist(slipID: Long): Boolean

    @Query("SELECT isSaved FROM slip where idSlip = :slipID")
    fun isSlipSaved(slipID: Long): Boolean

    @Query(
        "UPDATE slip SET isSaved = 1, slipNumber = :docNumber, slipDate = :docDate, " +
                "netAmount = :netAmount, vatAmount = 0, totalAmount = :netAmount, " +
                "idSlipType = :idSlipType, idStockTo = :stockID WHERE idSlip = :slipID"
    )
    fun slipSaved(
        slipID: Long,
        docNumber: String,
        docDate: String,
        netAmount: String,
        idSlipType: Long,
        stockID: Long
    )

}