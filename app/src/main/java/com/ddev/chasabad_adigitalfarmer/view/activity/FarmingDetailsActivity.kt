package com.ddev.chasabad_adigitalfarmer.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.ddev.chasabad_adigitalfarmer.R
import kotlinx.android.synthetic.main.activity_farming_details.*
import kotlinx.android.synthetic.main.activity_nursery_details.*
import kotlinx.android.synthetic.main.activity_nursery_details.nursery_details_image

class FarmingDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farming_details)

        val bundle:Bundle? = intent.extras
        farming_details_name.text = bundle!!.getString("name")
        val i: String = bundle!!.getString("details") +
                bundle!!.getString("details") +
                bundle!!.getString("details") +
                bundle!!.getString("details")
        farming_details_details.text = i
        Glide.with(this).load(bundle!!.getInt("image")).into(farming_details_image).toString();
        val j: String = bundle!!.getString("farming") +
                bundle!!.getString("farming") +
                bundle!!.getString("farming")
        farming_details_process.text = j
        val x: String = bundle!!.getString("disease") +
                bundle!!.getString("disease") +
                bundle!!.getString("disease")
        farming_details_disease.text = x
        val z: String = bundle!!.getString("vaccination") +
                bundle!!.getString("vaccination") +
                bundle!!.getString("vaccination")
        farming_details_vaccination.text = z
        farming_details_type.text = bundle!!.getString("type")
        farming_details_breed.text = bundle!!.getString("breed")
        farming_details_behave.text = bundle!!.getString("behave")
    }
}