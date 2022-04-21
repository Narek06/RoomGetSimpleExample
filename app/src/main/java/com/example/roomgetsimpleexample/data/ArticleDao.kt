package com.example.roomexample

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles;")
    fun getAll(): List<Article>

    @Insert
     fun insertAll(vararg arts: Article?)

     @Query("SELECT NOT EXISTS(SELECT * FROM articles WHERE title = :name )")
     fun isNotExists(name:String?):Boolean

    @Delete
    fun delete(art: Article)
}