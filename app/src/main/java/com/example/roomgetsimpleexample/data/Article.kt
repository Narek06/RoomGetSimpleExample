package com.example.roomexample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class Article(
    @ColumnInfo(name = "author")      val author: String?,
    @ColumnInfo(name = "title")       val title: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "url")         val url: String?,
    @ColumnInfo(name = "urlToImage")  val urlToImage: String?,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}
