package com.ksharshembie.whint.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Article(
    @PrimaryKey(autoGenerate = true)
    var idArticle: Long? = null,
    var articleCode: String,
    var articleName: String? = null
): Serializable
