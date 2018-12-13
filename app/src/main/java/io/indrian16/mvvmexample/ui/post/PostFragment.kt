package io.indrian16.mvvmexample.ui.post

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.ajalt.timberkt.d
import dagger.android.support.AndroidSupportInjection

import io.indrian16.mvvmexample.R
import io.indrian16.mvvmexample.data.model.Post
import io.indrian16.mvvmexample.ui.post.rv.PostRv
import io.indrian16.mvvmexample.util.fastToa
import io.indrian16.mvvmexample.util.toGone
import io.indrian16.mvvmexample.util.toVisible
import kotlinx.android.synthetic.main.fragment_post.*
import javax.inject.Inject

class PostFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener, PostRv.OnPostRvListener {

    companion object {

        fun newInstance() = PostFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var postViewModel: PostViewModel
    private val postAdapter = PostRv(this)

    private val postStateObserver = Observer<PostState> { state ->

        when (state) {

            is PostDefaultState -> {

                d { "Default State" }
                rvPost.toVisible()
                swipePost.isRefreshing = false
                postAdapter.add(state.postData)
            }

            is PostLoadingState -> {

                d { "Loading State" }
                rvPost.toGone()
                swipePost.isRefreshing = true
            }

            is PostErrorState -> {

                d { "PostFragment error = ${state.errorMessage}" }
                fastToa("Error ${state.errorMessage}")
            }
        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observeViewModel()

        savedInstanceState?.let {

            postViewModel.restorePost()
        } ?: postViewModel.updatePost()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postViewModel = ViewModelProviders.of(this, viewModelFactory)[PostViewModel::class.java]

        swipePost.setOnRefreshListener(this)
        rvPost.apply {

            layoutManager = LinearLayoutManager(context)
            adapter = postAdapter
        }
    }

    private fun observeViewModel() {

        postViewModel.postState.observe(this, postStateObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        postViewModel.postState.removeObserver(postStateObserver)
    }

    override fun onRefresh() {

        postAdapter.clear()
        postViewModel.refreshPost()
    }

    override fun onClickPost(post: Post) {

        fastToa("You Click ${post.title}")
    }
}
