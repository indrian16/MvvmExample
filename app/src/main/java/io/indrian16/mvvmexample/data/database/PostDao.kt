package io.indrian16.mvvmexample.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.indrian16.mvvmexample.data.model.Post
import io.reactivex.Single

@Dao
interface PostDao {

    @Query("SELECT * FROM posts")
    fun getPosts(): Single<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: Post)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPost(posts: List<Post>)
}