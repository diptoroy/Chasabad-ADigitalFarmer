package com.ddev.chasabad_adigitalfarmer.model.event

data class EventData(
    val eventName: String? = null,
    val eventDate: String? = null,
    val eventMonth: String? = null,
    val eventDetails: String? = null,
    val eventAuthor: String? = null,
    val eventLocation: String? = null,
    val eventId: String? = null,
    val eventCreateTime: Long = 0L
)
