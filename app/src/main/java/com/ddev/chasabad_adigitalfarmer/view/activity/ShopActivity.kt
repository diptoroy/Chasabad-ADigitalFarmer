package com.ddev.chasabad_adigitalfarmer.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.ShopSlide
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import kotlinx.android.synthetic.main.activity_shop.*

class ShopActivity : AppCompatActivity() {
    val sliderList = ArrayList<SlideModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        sliderList.add(SlideModel(R.drawable.developapp))
        sliderList.add(SlideModel(R.drawable.developapp))
        sliderList.add(SlideModel(R.drawable.developapp))

        val imageSlider = findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(sliderList)
    }
}

