package com.ddev.chasabad_adigitalfarmer.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.farming.FarmingData
import com.ddev.chasabad_adigitalfarmer.util.clickListener.FarmingOnItemClickListener
import com.ddev.chasabad_adigitalfarmer.view.adapter.DiseaseAdapter
import com.ddev.chasabad_adigitalfarmer.view.adapter.FarmingAdapter
import kotlinx.android.synthetic.main.activity_crop.*
import kotlinx.android.synthetic.main.activity_disease.*
import kotlinx.android.synthetic.main.activity_disease.toolbar
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

        setSupportActionBar(toolbar5)
        supportActionBar?.apply {
            val bundle:Bundle? = intent.extras
            toolbar5.title = bundle!!.getString("name")
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        val menuItem = menu!!.findItem(R.id.search_item)
        val searchView = menuItem.actionView as SearchView
        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                farmingAdapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                farmingAdapter.filter.filter(newText)
                Log.d("search", "===>$newText")
                return true
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}