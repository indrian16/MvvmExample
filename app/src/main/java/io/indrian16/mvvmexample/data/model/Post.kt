package io.indrian16.mvvmexample.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import io.indrian16.mvvmexample.util.AppConstant

/**
 *
 *  Sample data
 *  {
 *   "userId": 1,
 *   "id": 1,
 *   "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
 *   "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
 *   }
 * */

@Entity(tableName = AppConstant.TABLE_POST)
data class Post(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "body") val body: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "userId") val userId: Int
)