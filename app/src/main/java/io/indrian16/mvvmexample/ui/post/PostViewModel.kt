package io.indrian16.mvvmexample.ui.post

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.github.ajalt.timberkt.d
import io.indrian16.mvvmexample.data.model.Post
import io.indrian16.mvvmexample.data.repository.Repository
import io.indrian16.mvvmexample.util.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PostViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val postState = MutableLiveData<PostState>()

    init {

        postState.value = PostLoadingState(emptyList())
    }

    private fun obtainCurrentData() = postState.value?.postData ?: emptyList()

    fun updatePost() {

        d { "Update Post" }
        getPosts()
    }

    fun restorePost() {

        d { "Restore Post" }
        postState.value = PostDefaultState(obtainCurrentData())
    }

    fun refreshPost() {

        d { "Refresh Post" }
        postState.value = PostDefaultState(emptyList())
        getPosts()
    }

    private fun getPosts() {

        compositeDisposable += repository.getPosts()
            .debounce(400, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onPostReceived, this::onPostError)
    }

    private fun onPostReceived(posts: List<Post>) {

        d { "get from repository = ${posts.size}" }
        val currentData = obtainCurrentData().toMutableList()
        currentData.addAll(posts)
        d { "current data post size = ${posts.size}" }

        postState.value = PostDefaultState(currentData)
    }

    private fun onPostError(error: Throwable) {

        d { "post error ${error.message}" }
        postState.value = PostErrorState(obtainCurrentData(), "${error.message}")
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }
}