package com.ddev.chasabad_adigitalfarmer.model.crop

import com.google.gson.annotations.SerializedName

data class CropFertilizer(
    @SerializedName("fertilizerName")
    val fertilizerName: String,
    @SerializedName("fertilizerFirst")
    val fertilizerFirst: String,
    @SerializedName("fertilizerSecond")
    val fertilizerSecond: String,
    @SerializedName("fertilizerThird")
    val fertilizerThird: String
)
