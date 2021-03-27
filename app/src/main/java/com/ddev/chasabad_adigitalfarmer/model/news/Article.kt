package com.ddev.chasabad_adigitalfarmer.model.news

data class Article(
    val author: Any,
    val content: String,
    val description: Any,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: Any
)