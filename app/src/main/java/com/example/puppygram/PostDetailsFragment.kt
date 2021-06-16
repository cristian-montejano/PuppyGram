package com.example.puppygram

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.puppygram.models.Post
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class PostDetailsFragment : Fragment(R.layout.post_details_fragment) {

    companion object {
        fun newInstance(post: Post): PostDetailsFragment {
            val fragment = PostDetailsFragment()
            val bundle = Bundle().apply {
                putSerializable("post", post)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val post = arguments?.get("post")
        post?.let {
            bindPostDetails(view, it as Post)
        } ?: run {
            Toast.makeText(context, "Error showing post details", Toast.LENGTH_SHORT).show()
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val post = arguments?.get("post")
        post?.let {
            //bindPostDetails(it as Post)
        } ?: run {
            Toast.makeText(context, "Error showing post details", Toast.LENGTH_SHORT).show()
        }
    }

    private fun bindPostDetails(view: View, post: Post) {
        val image: ImageView = view.findViewById(R.id.post_image)
        val title: TextView = view.findViewById(R.id.post_title)
        val author: TextView = view.findViewById(R.id.post_author)
        val dateTaken: TextView = view.findViewById(R.id.post_date_taken)
        val datePublished: TextView = view.findViewById(R.id.post_date_published)
        val tags: TextView = view.findViewById(R.id.post_tags)

        if (post.media.m.isNotEmpty()) {
            Picasso.get().load(post.media.m)
                .into(image)
        }

        val takenParser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val publishedParser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val formatter = SimpleDateFormat("MM/dd/yyyy, h:mm a")

        title.text = getString(R.string.post_title, post.title)
        author.text = getString(R.string.post_author, post.author)
        dateTaken.text = getString(R.string.post_date_taken, formatter.format(takenParser.parse(post.date_taken)))
        datePublished.text = getString(R.string.post_date_published, formatter.format(publishedParser.parse(post.published)))
        tags.text = getString(R.string.post_tags, post.tags)

    }

}