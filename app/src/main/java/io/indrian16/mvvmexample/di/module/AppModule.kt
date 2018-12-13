package io.indrian16.mvvmexample.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import io.indrian16.mvvmexample.data.database.AppDatabase
import io.indrian16.mvvmexample.util.AppConstant
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideContext(application: Application) = application.applicationContext

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {

        return Room.databaseBuilder(context, AppDatabase::class.java, AppConstant.DB_NAME)
            .build()
    }

    @Provides
    fun providePostDao(appDatabase: AppDatabase) = appDatabase.postDao()
}