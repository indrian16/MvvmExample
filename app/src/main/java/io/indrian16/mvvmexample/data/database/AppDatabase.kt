package io.indrian16.mvvmexample.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import io.indrian16.mvvmexample.data.model.Post
import io.indrian16.mvvmexample.util.AppConstant

@Database(entities = [Post::class], version = AppConstant.DB_VER, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao
}