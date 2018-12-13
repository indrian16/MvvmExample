package io.indrian16.mvvmexample.ui.post.rv

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.indrian16.mvvmexample.R
import io.indrian16.mvvmexample.data.model.Post
import io.indrian16.mvvmexample.util.firstCapital
import kotlinx.android.synthetic.main.post_item.view.*

class PostRv(private val listener: OnPostRvListener) : RecyclerView.Adapter<PostRv.PostViewHolder>() {

    private var postList: List<Post> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item, parent, false)

        return PostViewHolder(view)
    }

    override fun getItemCount() = postList.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        holder.bind(postList[position])
    }

    fun add(posts: List<Post>) {

        postList = posts
        notifyDataSetChanged()
    }

    fun clear() {

        postList = emptyList()
        notifyDataSetChanged()
    }

    inner class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(post: Post) {

            itemView.apply {

                tvPostTitle.text = post.title.firstCapital()
                tvPostBody.text = post.body.firstCapital()
                setOnClickListener { listener.onClickPost(post) }
            }
        }
    }

    interface OnPostRvListener {

        fun onClickPost(post: Post)
    }
}