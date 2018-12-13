package io.indrian16.mvvmexample.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import io.indrian16.mvvmexample.AppMvmm
import io.indrian16.mvvmexample.di.builder.ActivityBuilder
import io.indrian16.mvvmexample.di.module.AppModule
import io.indrian16.mvvmexample.di.module.NetworkModule
import io.indrian16.mvvmexample.di.module.RepositoryModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ActivityBuilder::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(appMvmm: AppMvmm)
}