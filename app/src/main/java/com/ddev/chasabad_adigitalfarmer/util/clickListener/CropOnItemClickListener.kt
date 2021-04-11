package com.ddev.chasabad_adigitalfarmer.util.clickListener

import com.ddev.chasabad_adigitalfarmer.model.crop.CropData

interface CropOnItemClickListener {
    fun onClick(item: CropData, position: Int)
}