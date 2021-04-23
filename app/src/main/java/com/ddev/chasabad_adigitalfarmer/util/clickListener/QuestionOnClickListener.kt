package com.ddev.chasabad_adigitalfarmer.util.clickListener

import com.ddev.chasabad_adigitalfarmer.model.question.QuestionData

interface QuestionOnClickListener {
    fun onClick(item: QuestionData, position:Int)
}