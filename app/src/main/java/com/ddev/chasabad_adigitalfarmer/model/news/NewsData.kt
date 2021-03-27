package com.ddev.chasabad_adigitalfarmer.model.news

data class NewsData(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)