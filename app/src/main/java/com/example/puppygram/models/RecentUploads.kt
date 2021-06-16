package com.example.puppygram.models

data class RecentUploads(
    val title: String,
    val link: String,
    val description: String,
    val modified: String,
    val generator: String,
    val items: List<Post>
)
