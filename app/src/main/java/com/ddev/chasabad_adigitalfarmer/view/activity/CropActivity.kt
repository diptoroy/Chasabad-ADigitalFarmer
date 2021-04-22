package com.ddev.chasabad_adigitalfarmer.view.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.crop.CropData
import com.ddev.chasabad_adigitalfarmer.repository.Repository
import com.ddev.chasabad_adigitalfarmer.util.clickListener.CropOnItemClickListener
import com.ddev.chasabad_adigitalfarmer.view.adapter.CropAdapter
import com.ddev.chasabad_adigitalfarmer.view.viewmodel.CropActivityViewModel
import com.ddev.chasabad_adigitalfarmer.view.viewmodel.CropActivityViewModelFactory
import kotlinx.android.synthetic.main.activity_crop.*
import java.util.Locale.filter


class CropActivity : AppCompatActivity(), CropOnItemClickListener {

    private lateinit var viewModel: CropActivityViewModel
    private val cropAdapter by lazy { CropAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop)

        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            val bundle:Bundle? = intent.extras
            toolbar.title = bundle!!.getString("name")
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }


        val repository = Repository()
        val viewModelFactory = CropActivityViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CropActivityViewModel::class.java)

        viewModel.getCropData()
        viewModel.cropResponse.observe(this, Observer { response ->
            cropAdapter.setData(response)
                Log.d("dCrop", response.toString())

        })

        setUpCrops()
    }


    private fun setUpCrops() {
        crop_recyclerview.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )
        crop_recyclerview.setHasFixedSize(true)
//        val fertilizerList = ArrayList<CropFertilizer>()
//        fertilizerList.add(CropFertilizer("TSP", "22", "12", "9"))
//        fertilizerList.add(CropFertilizer("Urea", "22", "12", "9"))
//        fertilizerList.add(CropFertilizer("Dap", "22", "12", "9"))
//        fertilizerList.add(CropFertilizer("Potash", "22", "12", "9"))
//        fertilizerList.add(CropFertilizer("Calcium", "22", "12", "9"))
//

//        cropList.add(
//            CropData(
//                R.drawable.rice.toString(), "ফসলের নামঃ ধান", "মার্চ - জুলাই",
//                "ফসল পরিচিতিঃ ধান বাংলাদেশের সবচেয়ে চাষ করা ফসল।ধান থেকে চাল হয়।চাল থেকে তৈরী হয় ভাত,যা বাঙ্গালীর খুব জনপ্রিয় ও মূল খাদ্য",
//                "ফসল পরিচিতিঃ ধান বাংলাদেশের সবচেয়ে চাষ করা ফসল।ধান থেকে চাল হয়।চাল থেকে তৈরী হয় ভাত,যা বাঙ্গালীর খুব জনপ্রিয় ও মূল খাদ্য",
//                "Amon", fertilizerList
//            )
//        )
//        cropList.add(
//            CropData(
//                R.drawable.rice.toString(), "ফসলের নামঃ Onion", "মার্চ - জুলাই",
//                "ফসল পরিচিতিঃ ধান বাংলাদেশের সবচেয়ে চাষ করা ফসল।ধান থেকে চাল হয়।চাল থেকে তৈরী হয় ভাত,যা বাঙ্গালীর খুব জনপ্রিয় ও মূল খাদ্য",
//                "ফসল পরিচিতিঃ ধান বাংলাদেশের সবচেয়ে চাষ করা ফসল।ধান থেকে চাল হয়।চাল থেকে তৈরী হয় ভাত,যা বাঙ্গালীর খুব জনপ্রিয় ও মূল খাদ্য",
//                "Amon", fertilizerList
//            )
//        )
//        cropList.add(
//            CropData(
//                R.drawable.rice.toString(), "ফসলের নামঃ Potato", "মার্চ - জুলাই",
//                "ফসল পরিচিতিঃ ধান বাংলাদেশের সবচেয়ে চাষ করা ফসল।ধান থেকে চাল হয়।চাল থেকে তৈরী হয় ভাত,যা বাঙ্গালীর খুব জনপ্রিয় ও মূল খাদ্য",
//                "ফসল পরিচিতিঃ ধান বাংলাদেশের সবচেয়ে চাষ করা ফসল।ধান থেকে চাল হয়।চাল থেকে তৈরী হয় ভাত,যা বাঙ্গালীর খুব জনপ্রিয় ও মূল খাদ্য",
//                "Amon", fertilizerList
//            )
//        )
//        cropList.add(
//            CropData(
//                R.drawable.rice.toString(), "ফসলের নামঃ ধান", "মার্চ - জুলাই",
//                "ফসল পরিচিতিঃ ধান বাংলাদেশের সবচেয়ে চাষ করা ফসল।ধান থেকে চাল হয়।চাল থেকে তৈরী হয় ভাত,যা বাঙ্গালীর খুব জনপ্রিয় ও মূল খাদ্য",
//                "ফসল পরিচিতিঃ ধান বাংলাদেশের সবচেয়ে চাষ করা ফসল।ধান থেকে চাল হয়।চাল থেকে তৈরী হয় ভাত,যা বাঙ্গালীর খুব জনপ্রিয় ও মূল খাদ্য",
//                "Amon", fertilizerList
//            )
//        )
//        cropAdapter.setData(cropList)
        crop_recyclerview.adapter = cropAdapter
    }

    override fun onClick(item: CropData, position: Int) {
        val intent = Intent(this, CropDetailsActivity::class.java)
        intent.putExtra("name", item.cropName)
        intent.putExtra("image", item.cropImage)
        intent.putExtra("intro", item.cropDetails)
        intent.putExtra("process", item.cropProcess)
        intent.putExtra("type", item.cropType)
        intent.putExtra("time", item.cropTime)
        intent.putExtra("fertilizer", item.cropFertilizer[position].fertilizerName)
        intent.putExtra("fertilizerFirst", item.cropFertilizer[position].fertilizerFirst)
        intent.putExtra("fertilizerSecond", item.cropFertilizer[position].fertilizerSecond)
        intent.putExtra("fertilizerThird", item.cropFertilizer[position].fertilizerThird)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        val menuItem = menu!!.findItem(R.id.search_item)
        val searchView = menuItem.actionView as SearchView
        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                cropAdapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                cropAdapter.filter.filter(newText)
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


