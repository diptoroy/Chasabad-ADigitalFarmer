package com.ddev.chasabad_adigitalfarmer.util.clickListener

import com.ddev.chasabad_adigitalfarmer.model.nursery.NurseryData

interface NurseryOnItemClickListener {
    fun onClick(item: NurseryData, position: Int)
}