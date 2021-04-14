package com.ddev.chasabad_adigitalfarmer.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.farming.FarmingData
import com.ddev.chasabad_adigitalfarmer.util.clickListener.FarmingOnItemClickListener
import com.ddev.chasabad_adigitalfarmer.view.adapter.DiseaseAdapter
import com.ddev.chasabad_adigitalfarmer.view.adapter.FarmingAdapter
import kotlinx.android.synthetic.main.activity_disease.*
import kotlinx.android.synthetic.main.activity_farming.*

class FarmingActivity : AppCompatActivity(),FarmingOnItemClickListener {

    private val farmingAdapter by lazy { FarmingAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farming)

        val farmingList = ArrayList<FarmingData>()
        farmingList.add(
            FarmingData("Cow",R.drawable.cow,R.drawable.cow,
            "Domestic","","","","",
                "","")
        )
        farmingList.add(FarmingData("Buffalo",R.drawable.buffalo,R.drawable.buffalo,
            "Domestic",
            "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
            "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
            "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
            "Belgium",
            "Friendly",
            "The cultivation of rice begins by planting water-soaked seeds in a properly prepared"))

        farmingList.add(FarmingData("Buffalo",R.drawable.buffalo,R.drawable.buffalo,
            "Domestic",
            "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
            "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
            "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
            "Belgium",
            "Friendly",
            "The cultivation of rice begins by planting water-soaked seeds in a properly prepared"))


        farmingList.add(FarmingData("Buffalo",R.drawable.buffalo,R.drawable.buffalo,
            "Domestic",
            "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
            "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
            "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
            "Belgium",
            "Friendly",
            "The cultivation of rice begins by planting water-soaked seeds in a properly prepared"))

        farmingList.add(FarmingData("Buffalo",R.drawable.buffalo,R.drawable.buffalo,
            "Domestic",
            "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
            "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
            "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
            "Belgium",
            "Friendly",
            "The cultivation of rice begins by planting water-soaked seeds in a properly prepared"))

        farmingList.add(FarmingData("Buffalo",R.drawable.buffalo,R.drawable.buffalo,
            "Domestic",
            "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
            "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
            "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
            "Belgium",
            "Friendly",
            "The cultivation of rice begins by planting water-soaked seeds in a properly prepared"))

        farmingAdapter.setData(farmingList)
        setUpFarmingRecyclerview()
    }

    private fun setUpFarmingRecyclerview() {
        farming_recyclerview.layoutManager = StaggeredGridLayoutManager(2,
            StaggeredGridLayoutManager.VERTICAL)
        farming_recyclerview.setHasFixedSize(true)
        farming_recyclerview.adapter = farmingAdapter
    }

    override fun onClick(item: FarmingData, position: Int) {
        val intent = Intent(this, FarmingDetailsActivity::class.java)
        intent.putExtra("name", item.farmingName)
        intent.putExtra("image", item.farmingImage)
        intent.putExtra("type", item.farmingType)
        intent.putExtra("breed", item.farmingBreed)
        intent.putExtra("behave", item.farmingBehave)
        intent.putExtra("details", item.farmingDetails)
        intent.putExtra("farming", item.farmingFarming)
        intent.putExtra("disease", item.farmingDisease)
        intent.putExtra("vaccination", item.farmingVaccination)
        startActivity(intent)
    }
}