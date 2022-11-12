package com.ksharshembie.whint.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ksharshembie.whint.local.room.Article

@Dao
interface ArticleDao {

    @Insert
    fun insert(article: Article)

    @Query("SELECT * FROM article WHERE articleCode = :code")
    fun getArticle(code : String): Article

    @Query("SELECT EXISTS(SELECT * FROM article WHERE articleCode = :code)")
    fun isRowIsExist(code : String) : Boolean
}