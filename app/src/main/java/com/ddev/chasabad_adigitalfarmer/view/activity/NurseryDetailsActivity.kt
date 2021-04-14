package com.ddev.chasabad_adigitalfarmer.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.ddev.chasabad_adigitalfarmer.R
import kotlinx.android.synthetic.main.activity_crop_details.*
import kotlinx.android.synthetic.main.activity_nursery_details.*

class NurseryDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nursery_details)

        val bundle:Bundle? = intent.extras
        nursery_details_name.text = bundle!!.getString("name")
        val i: String = bundle!!.getString("details") +
                bundle!!.getString("details") +
                bundle!!.getString("details") +
                bundle!!.getString("details")
        nursery_deyails_details.text = i
        Glide.with(this).load(bundle!!.getInt("image")).into(nursery_details_image).toString();
        Log.d("image",bundle!!.getString("image").toString())
        val j: String = bundle!!.getString("process") +
                bundle!!.getString("process") +
                bundle!!.getString("process")
        nursery_deyails_process.text = j
        val x: String = bundle!!.getString("use") +
                bundle!!.getString("use") +
                bundle!!.getString("use")
        nursery_deyails_use.text = x
        nursery_deyails_family.text = bundle!!.getString("family")
        nursery_deyails_origin.text = bundle!!.getString("origin")
        nursery_deyails_category.text = bundle!!.getString("category")
        nursery_deyails_temp.text = bundle!!.getString("temp")
        nursery_details_height.text = bundle!!.getString("height")
        nursery_deyails_hervesting.text = bundle!!.getString("hervesting")
        nursery_deyails_cultivation.text = bundle!!.getString("cultivation")
    }
}