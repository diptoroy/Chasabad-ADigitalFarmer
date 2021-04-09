package com.ddev.chasabad_adigitalfarmer.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.crop.CropData
import com.ddev.chasabad_adigitalfarmer.model.crop.CropFertilizer
import com.ddev.chasabad_adigitalfarmer.repository.Repository
import com.ddev.chasabad_adigitalfarmer.view.adapter.ArticleAdapter
import com.ddev.chasabad_adigitalfarmer.view.adapter.CropAdapter
import com.ddev.chasabad_adigitalfarmer.view.adapter.FertilizerAdapter
import com.ddev.chasabad_adigitalfarmer.view.viewmodel.ArticleActivityViewModel
import com.ddev.chasabad_adigitalfarmer.view.viewmodel.ArticleActivityViewModelFactory
import com.ddev.chasabad_adigitalfarmer.view.viewmodel.CropDetailsActivityViewModel
import com.ddev.chasabad_adigitalfarmer.view.viewmodel.CropDetailsActivityViewModelFactory
import kotlinx.android.synthetic.main.activity_article_details.*
import kotlinx.android.synthetic.main.activity_crop.*
import kotlinx.android.synthetic.main.activity_crop_details.*

class CropDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: CropDetailsActivityViewModel
    private val fertlizerAdapter by lazy { FertilizerAdapter() }

    private val cropFertilizerAdapter by lazy { FertilizerAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop_details)

        val bundle:Bundle? = intent.extras
        crop_details_name.text = bundle!!.getString("name")
        val i: String = bundle!!.getString("intro") +
                bundle!!.getString("intro") +
                bundle!!.getString("intro") +
                bundle!!.getString("intro")
        crop_details_intro.text = i
        Glide.with(this).load(bundle.getString("image")).into(crop_details_image);
        val j: String = bundle!!.getString("process") +
                bundle!!.getString("process") +
                bundle!!.getString("process")
        crop_details_process.text = j
        crop_details_time.text = bundle!!.getString("time")
        crop_details_type.text = bundle!!.getString("type")
//        var fertilizerName: String? = bundle.getString("fertilizer")
//        var fertilizerFirst: String? = bundle.getString("fertilizerFirst")
//        var fertilizerSecond: String? = bundle.getString("fertilizerSecond")
//        var fertilizerThird: String? = bundle.getString("fertilizerThird")

        val repository = Repository()
        val viewModelFactory = CropDetailsActivityViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CropDetailsActivityViewModel::class.java)

        viewModel.getFertilizer()
        viewModel.fResponse.observe(this, Observer {
            cropFertilizerAdapter.setData(it)
        })

        crop_details_recyclerview.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )
        crop_details_recyclerview.setHasFixedSize(true)
//        val fertilizerList = ArrayList<CropFertilizer>()
//        fertilizerList.add(CropFertilizer(fertilizerName.toString(),fertilizerFirst.toString(),fertilizerSecond.toString(),fertilizerThird.toString()))
//        cropFertilizerAdapter.setData(fertilizerList)
        crop_details_recyclerview.adapter = cropFertilizerAdapter

    }
}