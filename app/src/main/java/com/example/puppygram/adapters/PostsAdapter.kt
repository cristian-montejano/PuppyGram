package com.example.puppygram.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.puppygram.R
import com.example.puppygram.models.Post
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class PostsAdapter(private val items: List<Post>,
                   private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    interface OnItemClickListener {
        fun onPostItemClicked(post: Post)
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) : PostViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.post_item, viewGroup, false)

        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = items.get(position)
        holder.bind(post, itemClickListener)
    }


    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val postContainer: ConstraintLayout = itemView.findViewById(R.id.post_container)
        private val postImage: ImageView = itemView.findViewById(R.id.post_image)
        private val postTitle: TextView = itemView.findViewById(R.id.post_title)
        private val postTimestamp: TextView = itemView.findViewById(R.id.post_timestamp)

        fun bind(post: Post, itemClickListener: OnItemClickListener) {
            postContainer.setOnClickListener {
                itemClickListener.onPostItemClicked(post)
            }

            if (post.media.m.isNotEmpty()) {
                Picasso.get().load(post.media.m)
                    .into(postImage)
            }

            postTitle.text = post.title


            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val formatter = SimpleDateFormat("MM/dd/yyyy, h:mm a")
            postTimestamp.text = formatter.format(parser.parse(post.published))

        }

    }


}