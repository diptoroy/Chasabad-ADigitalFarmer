package com.ddev.chasabad_adigitalfarmer.util

import com.ddev.chasabad_adigitalfarmer.model.news.NewsData

interface OnItemClickListener {
    fun onClick(item:NewsData,position: Int)
}