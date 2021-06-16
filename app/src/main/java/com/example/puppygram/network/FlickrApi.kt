package com.example.puppygram.network

import com.example.puppygram.models.RecentUploads
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {

    companion object {

        fun create(): FlickrApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("https://api.flickr.com/")
                .build()

            return retrofit.create(FlickrApi::class.java)
        }

    }

    @GET("services/feeds/photos_public.gne")
    fun getRecentUploads(@Query("tags") tags : String,
                         @Query("format") format : String,
                         @Query("nojsoncallback") nojsoncallback: Int) : Call<RecentUploads>

}