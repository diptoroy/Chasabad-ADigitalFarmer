package com.ddev.chasabad_adigitalfarmer.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ddev.chasabad_adigitalfarmer.R
import kotlinx.android.synthetic.main.activity_crop_details.*

class CropDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop_details)

        val bundle:Bundle? = intent.extras
//        crop.text = bundle!!.getString("name")
    }
}