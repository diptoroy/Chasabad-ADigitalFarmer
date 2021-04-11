package com.ddev.chasabad_adigitalfarmer.util.clickListener

import com.ddev.chasabad_adigitalfarmer.model.shop.ShopData

interface ShopOnItemClickListener {
    fun onClick(item:ShopData,position: Int)
}