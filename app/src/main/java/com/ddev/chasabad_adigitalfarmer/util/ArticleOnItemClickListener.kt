package com.ddev.chasabad_adigitalfarmer.util

import com.ddev.chasabad_adigitalfarmer.model.news.Article
import com.ddev.chasabad_adigitalfarmer.model.news.NewsData

interface ArticleOnItemClickListener {
    fun onClick(item:Article,position: Int)
}