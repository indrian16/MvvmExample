package io.indrian16.mvvmexample.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import io.indrian16.mvvmexample.data.api.PostService
import io.indrian16.mvvmexample.data.database.PostDao
import io.indrian16.mvvmexample.data.repository.Repository
import io.indrian16.mvvmexample.data.repository.RepositoryImpl

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(context: Context, postDao: PostDao, postService: PostService): Repository {

        return RepositoryImpl(context, postDao, postService)
    }
}