package com.ddev.chasabad_adigitalfarmer.model.crop

import com.google.gson.annotations.SerializedName

data class CropData(
    @SerializedName("cropImage")
    val cropImage: String,
    @SerializedName("cropName")
    val cropName: String,
    @SerializedName("cropTime")
    val cropTime: String,
    @SerializedName("cropDetails")
    val cropDetails: String,
    @SerializedName("cropProcess")
    val cropProcess: String,
    @SerializedName("cropType")
    val cropType: String,
    @SerializedName("cropFertilizer")
    val cropFertilizer: List<CropFertilizer>
)