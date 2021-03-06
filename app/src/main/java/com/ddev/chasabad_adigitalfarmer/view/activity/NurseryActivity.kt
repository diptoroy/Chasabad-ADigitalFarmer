package com.ddev.chasabad_adigitalfarmer.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.nursery.NurseryData
import com.ddev.chasabad_adigitalfarmer.util.clickListener.NurseryOnItemClickListener
import com.ddev.chasabad_adigitalfarmer.view.adapter.CropAdapter
import com.ddev.chasabad_adigitalfarmer.view.adapter.NurseryAdapter
import kotlinx.android.synthetic.main.activity_crop.*
import kotlinx.android.synthetic.main.activity_nursery.*

class NurseryActivity : AppCompatActivity(), NurseryOnItemClickListener {

    private val nurseryAdapter by lazy { NurseryAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nursery)

        setSupportActionBar(toolbar1)
        supportActionBar?.apply {
            val bundle:Bundle? = intent.extras
            toolbar1.title = bundle!!.getString("name")
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        setUpNursery()
    }

    private fun setUpNursery() {
        nursery_recyclerview.layoutManager = StaggeredGridLayoutManager(
            2,
            LinearLayoutManager.VERTICAL
        )
        nursery_recyclerview.setHasFixedSize(true)
        val nurseryList = ArrayList<NurseryData>()
        nurseryList.add(
            NurseryData(
                R.drawable.nurserybackimageopen, "Corsica", "Agavoideae", "American And Caribian",
                "24 inch to 30 inch", "Indoor", "21 to 27 cel", "90-100 days", "Transplanted",
                "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
                "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
                "The cultivation of rice begins by planting water-soaked seeds in a properly prepared"
            )
        )

        nurseryList.add(
            NurseryData(
                R.drawable.nurserybackimageopen, "Avocoda", "Agavoideae", "American And Caribian",
                "24 inch to 30 inch", "Indoor", "21 to 27 cel", "90-100 days", "Transplanted",
                "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
                "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
                "The cultivation of rice begins by planting water-soaked seeds in a properly prepared"
            )
        )

        nurseryList.add(
            NurseryData(
                R.drawable.nurserybackimageopen, "Mango", "Agavoideae", "American And Caribian",
                "24 inch to 30 inch", "Indoor", "21 to 27 cel", "90-100 days", "Transplanted",
                "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
                "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
                "The cultivation of rice begins by planting water-soaked seeds in a properly prepared"
            )
        )

        nurseryList.add(
            NurseryData(
                R.drawable.nurserybackimageopen, "Corsica", "Agavoideae", "American And Caribian",
                "24 inch to 30 inch", "Indoor", "21 to 27 cel", "90-100 days", "Transplanted",
                "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
                "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
                "The cultivation of rice begins by planting water-soaked seeds in a properly prepared"
            )
        )
        nurseryAdapter.setData(nurseryList)
        nursery_recyclerview.adapter = nurseryAdapter

    }

    override fun onClick(item: NurseryData, position: Int) {
        val intent = Intent(this, NurseryDetailsActivity::class.java)
        intent.putExtra("name", item.nurseryName)
        intent.putExtra("image", item.nurseryImage)
        intent.putExtra("family", item.nurseryFamilyName)
        intent.putExtra("origin", item.nurseryOrigin)
        intent.putExtra("height", item.nurseryHeight)
        intent.putExtra("category", item.nurseryCategory)
        intent.putExtra("temp", item.nurseryTemp)
        intent.putExtra("hervesting", item.nurseryHervesting)
        intent.putExtra("cultivation", item.nurseryCultivation)
        intent.putExtra("details", item.nurseryDetails)
        intent.putExtra("process", item.nurseryProcess)
        intent.putExtra("use", item.nurseryUse)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        val menuItem = menu!!.findItem(R.id.search_item)
        val searchView = menuItem.actionView as SearchView
        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                nurseryAdapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                nurseryAdapter.filter.filter(newText)
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