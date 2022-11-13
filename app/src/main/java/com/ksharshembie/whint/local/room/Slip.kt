package com.ksharshembie.whint.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Slip(
    @PrimaryKey(autoGenerate = true)
    var idSlip: Long? = null,
    var slipNumber: String,
    var slipDate: Long,
    var idDate: Long,
    var idChangeDate: Long? = null,
    var idCancelDate: Long? = null,
    var idVendor: Long? = null,
    var netAmount: String,
    var vatAmount: String,
    var totalAmount: String
)
