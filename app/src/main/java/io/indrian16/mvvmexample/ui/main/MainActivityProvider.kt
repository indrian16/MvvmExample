package io.indrian16.mvvmexample.ui.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.indrian16.mvvmexample.ui.post.PostFragment
import io.indrian16.mvvmexample.ui.post.PostFragmentModule

@Module
abstract class MainActivityProvider {

    @ContributesAndroidInjector(modules = [PostFragmentModule::class])
    abstract fun providePostFragment(): PostFragment
}