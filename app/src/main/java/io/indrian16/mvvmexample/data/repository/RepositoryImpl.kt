package io.indrian16.mvvmexample.data.repository

import android.content.Context
import com.github.ajalt.timberkt.d
import io.indrian16.mvvmexample.data.api.PostService
import io.indrian16.mvvmexample.data.database.PostDao
import io.indrian16.mvvmexample.data.model.Post
import io.indrian16.mvvmexample.util.isConnAvailable
import io.reactivex.Observable
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val context: Context,
                                         private val postDao: PostDao,
                                         private val postService: PostService) : Repository {

    override fun getPosts(): Observable<List<Post>> {

        return if (context.isConnAvailable()) {

            Observable.concat(getPostDbLocal(), getPostApiLocal())

        } else {

            getPostDbLocal()
        }
    }

    private fun getPostDbLocal(): Observable<List<Post>> {

        return postDao.getPosts()
            .filter { it.isNotEmpty() }
            .toObservable()
            .doOnNext { d { "get from api ${it.size}" } }
    }

    private fun getPostApiLocal(): Observable<List<Post>> {

        return postService.getPosts()
            .toObservable()
            .doOnNext {

                d { "get from db ${it.size}" }
                d { "insert post ${it.size}" }
                postDao.insertAllPost(it)
            }
    }
}