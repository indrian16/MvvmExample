package io.indrian16.mvvmexample.data.api

import io.indrian16.mvvmexample.data.model.Post
import io.reactivex.Single
import retrofit2.http.GET

interface PostService {

    @GET("/posts")
    fun getPosts(): Single<List<Post>>
}