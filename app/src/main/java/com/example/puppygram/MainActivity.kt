package com.example.puppygram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.puppygram.models.Post
import com.example.puppygram.models.RecentUploads
import com.example.puppygram.network.FlickrApi
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

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

    }
}