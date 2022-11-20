package com.ksharshembie.whint.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SlipType(
    @PrimaryKey
    var idSlipType: Long,
    var slipType: String
)

// id = 1 ; SlipType = StockIn
