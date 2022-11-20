package com.ksharshembie.whint.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Slip(
    @PrimaryKey(autoGenerate = true)
    var idSlip: Long? = null,
    var slipNumber: String? = null,
    var slipDate: String? = null,
    var idDate: Long = System.currentTimeMillis(),
    var idChangeDate: Long? = null,
    var idCancelDate: Long? = null,
    var idVendor: Long? = null,
    var netAmount: String? = null,
    var vatAmount: String? = null,
    var totalAmount: String? = null,
    var isSaved: Boolean = false,
    var idStockTo: Long? = null,
    var idStockFrom: Long? = null,
    var idSlipType: Long? = null
)
