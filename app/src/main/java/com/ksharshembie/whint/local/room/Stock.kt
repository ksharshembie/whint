package com.ksharshembie.whint.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Stock(
    @PrimaryKey(autoGenerate = true)
    var idStock: Long,
    var code: String,
    var name: String,
    var address: String? = null
)
