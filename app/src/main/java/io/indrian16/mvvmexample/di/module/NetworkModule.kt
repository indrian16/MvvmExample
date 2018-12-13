package io.indrian16.mvvmexample.di.module

import dagger.Module
import dagger.Provides
import io.indrian16.mvvmexample.data.api.PostService
import io.indrian16.mvvmexample.util.AppConstant
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl(AppConstant.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()!!

    @Provides
    fun providePostService(retrofit: Retrofit) = retrofit.create(PostService::class.java)!!
}