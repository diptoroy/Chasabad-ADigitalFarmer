package com.ddev.chasabad_adigitalfarmer.model.marketPrice

data class MarketPriceData(
    val productName: String,
    val productCurrentSellPrice: String,
    val productCurrentBuyPrice: String,
    val productPreviousSellPrice: String,
    val productPreviousBuyPrice: String
)
