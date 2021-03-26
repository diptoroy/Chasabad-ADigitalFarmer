package com.ddev.chasabad_adigitalfarmer.model.event

data class EventData(
    val eventName: String,
    val eventDate: String,
    val eventMonth: String,
    val eventImage: Int,
    val eventDetails: String,
    val eventAuthor: String,
    val eventLocation: String
)
