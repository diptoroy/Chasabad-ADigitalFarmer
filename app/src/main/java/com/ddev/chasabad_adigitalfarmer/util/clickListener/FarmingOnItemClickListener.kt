package com.ddev.chasabad_adigitalfarmer.util.clickListener

import com.ddev.chasabad_adigitalfarmer.model.farming.FarmingData

interface FarmingOnItemClickListener {
    fun onClick(item:FarmingData,position: Int)
}