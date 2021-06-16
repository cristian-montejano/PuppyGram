package com.example.puppygram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puppygram.adapters.PostsAdapter
import com.example.puppygram.models.Post
import com.example.puppygram.models.RecentUploads
import com.example.puppygram.network.FlickrApi
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), PostsAdapter.OnItemClickListener {

    private val apiService by lazy {
        FlickrApi.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()
    }

    private fun getData() {
        val call = apiService.getRecentUploads("puppy", "json", 1)
        call.enqueue(object : retrofit2.Callback<RecentUploads> {
            override fun onResponse(call: Call<RecentUploads>, response: Response<RecentUploads>) {
                bindPostRecyclerView(response.body()?.items)
            }

            override fun onFailure(call: Call<RecentUploads>, t: Throwable) {
                Log.d("api", "${t.message}")
            }
        })
    }

    private fun bindPostRecyclerView(posts: List<Post>?) {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = posts?.let { PostsAdapter(it, this) }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onPostItemClicked(post: Post) {
        Log.i("post", "${post.title} clicked")
        launchPostDetailsFragment(post)
    }

    private fun launchPostDetailsFragment(post: Post) {
        val fm = supportFragmentManager
        fm.beginTransaction()
            .replace(R.id.post_details_fragment_view, PostDetailsFragment.newInstance(post))
            .addToBackStack(null)
            .commit()
    }
}