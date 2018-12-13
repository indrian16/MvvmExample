package io.indrian16.mvvmexample.data.repository

import io.indrian16.mvvmexample.data.model.Post
import io.reactivex.Observable

interface Repository {

    fun getPosts(): Observable<List<Post>>

}