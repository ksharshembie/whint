package com.ksharshembie.whint.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SlipItem(
    @PrimaryKey(autoGenerate = true)
    var idSlipItem: Long? = null,
    var idSlip: Long? = null,
    var idArticle: Long,
    var quantity: Int,
    var price: Long,
    var idDate: Long
)
