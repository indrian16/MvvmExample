package io.indrian16.mvvmexample.ui.post

import android.arch.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import io.indrian16.mvvmexample.ViewModelProviderFactory
import io.indrian16.mvvmexample.data.repository.Repository

@Module
class PostFragmentModule {

    @Provides
    fun providePostViewModel(repository: Repository) = PostViewModel(repository)

    @Provides
    fun providePostViewModelFactory(postViewModel: PostViewModel): ViewModelProvider.Factory {

        return ViewModelProviderFactory(postViewModel)
    }
}