package io.indrian16.mvvmexample.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.indrian16.mvvmexample.ui.main.MainActivity
import io.indrian16.mvvmexample.ui.main.MainActivityProvider

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityProvider::class])
    abstract fun bindMainActivity(): MainActivity
}