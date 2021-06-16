package com.example.puppygram.models

import java.io.Serializable

data class Post(

    val title: String,
    val link: String,
    val media: Media,
    val date_taken: String,
    val description: String,
    val published: String,
    val author: String,
    val author_id: String,
    val tags: String
) : Serializable

data class Media(
    val m: String
)
