package io.indrian16.mvvmexample.ui.post

import io.indrian16.mvvmexample.data.model.Post

sealed class PostState {

    abstract val postData: List<Post>
}

data class PostDefaultState(override val postData: List<Post>): PostState()
data class PostLoadingState(override val postData: List<Post>): PostState()
data class PostErrorState(override val postData: List<Post>, val errorMessage: String): PostState()
