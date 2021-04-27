package com.ddev.chasabad_adigitalfarmer.util.clickListener

import com.ddev.chasabad_adigitalfarmer.model.event.EventData

interface EventOnClickListener {
    fun onClick(item: EventData,position: Int)
}