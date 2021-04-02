package com.ddev.chasabad_adigitalfarmer.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.crop.CropData
import com.ddev.chasabad_adigitalfarmer.util.CropOnItemClickListener
import com.ddev.chasabad_adigitalfarmer.view.adapter.CropAdapter
import kotlinx.android.synthetic.main.activity_crop.*


class CropActivity : AppCompatActivity(),CropOnItemClickListener {

    private val cropAdapter by lazy { CropAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop)

        setUpCrops()
    }

    private fun setUpCrops() {
        crop_recyclerview.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )
        crop_recyclerview.setHasFixedSize(true)
        val cropList = ArrayList<CropData>()
        cropList.add(
            CropData(
                R.drawable.rice, "ফসলের নামঃ ধান", "মার্চ - জুলাই",
                "ফসল পরিচিতিঃ ধান বাংলাদেশের সবচেয়ে চাষ করা ফসল।ধান থেকে চাল হয়।চাল থেকে তৈরী হয় ভাত,যা বাঙ্গালীর খুব জনপ্রিয় ও মূল খাদ্য"
            )
        )
        cropList.add(
            CropData(
                R.drawable.rice, "ফসলের নামঃ ধান", "মার্চ - জুলাই",
                "ফসল পরিচিতিঃ ধান বাংলাদেশের সবচেয়ে চাষ করা ফসল।ধান থেকে চাল হয়।চাল থেকে তৈরী হয় ভাত,যা বাঙ্গালীর খুব জনপ্রিয় ও মূল খাদ্য"
            )
        )
        cropList.add(
            CropData(
                R.drawable.rice, "ফসলের নামঃ ধান", "মার্চ - জুলাই",
                "ফসল পরিচিতিঃ ধান বাংলাদেশের সবচেয়ে চাষ করা ফসল।ধান থেকে চাল হয়।চাল থেকে তৈরী হয় ভাত,যা বাঙ্গালীর খুব জনপ্রিয় ও মূল খাদ্য"
            )
        )
        cropList.add(
            CropData(
                R.drawable.rice, "ফসলের নামঃ ধান", "মার্চ - জুলাই",
                "ফসল পরিচিতিঃ ধান বাংলাদেশের সবচেয়ে চাষ করা ফসল।ধান থেকে চাল হয়।চাল থেকে তৈরী হয় ভাত,যা বাঙ্গালীর খুব জনপ্রিয় ও মূল খাদ্য"
            )
        )
        cropAdapter.setData(cropList)
        crop_recyclerview.adapter = cropAdapter
    }

    override fun onClick(item: CropData, position: Int) {
//        val objects = CropsDetailsFragment()
//        //objects
//        val newBundle = Bundle()
//        newBundle.putString("name", item.cropName)
//        objects.arguments = newBundle
//        Log.d("pos", position.toString())
        val intent = Intent(this, CropDetailsActivity::class.java)
        intent.putExtra("name",item.cropName)
        startActivity(intent)
    }
}

