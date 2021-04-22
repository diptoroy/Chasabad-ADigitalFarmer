package com.ddev.chasabad_adigitalfarmer.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.disease.DiseaseData
import com.ddev.chasabad_adigitalfarmer.util.clickListener.DiseaseOnItemClickListener
import com.ddev.chasabad_adigitalfarmer.view.adapter.DiseaseAdapter
import com.ddev.chasabad_adigitalfarmer.view.fragment.DiseaseDetailsFragment
import kotlinx.android.synthetic.main.activity_crop.*
import kotlinx.android.synthetic.main.activity_disease.*
import kotlinx.android.synthetic.main.activity_disease.toolbar

class DiseaseActivity : AppCompatActivity(), DiseaseOnItemClickListener {

    private val diseaseAdapter by lazy { DiseaseAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disease)

        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            val bundle:Bundle? = intent.extras
            toolbar.title = bundle!!.getString("name")
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val diseaseList = ArrayList<DiseaseData>()
        diseaseList.add(DiseaseData("Bacterial leaf streak",R.drawable.cropde,
            "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
            "Rice,Jute","Antibiotics, Agrimycin 100, Agrimycin 500, Agric",
            "(Agrimycin)",
            "The cultivation of rice begins by planting water-soaked seeds in a properly",
            "Xanthomonas oryzae"))

        diseaseList.add(DiseaseData("Bacterial leaf streak",R.drawable.cropde,
            "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
            "Rice,Jute","Antibiotics, Agrimycin 100, Agrimycin 500, Agric",
            "(Agrimycin)",
            "The cultivation of rice begins by planting water-soaked seeds in a properly",
            "Xanthomonas oryzae"))

        diseaseList.add(DiseaseData("Bacterial leaf streak",R.drawable.cropde,
            "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
            "Rice,Jute","Antibiotics, Agrimycin 100, Agrimycin 500, Agric",
            "(Agrimycin)",
            "The cultivation of rice begins by planting water-soaked seeds in a properly",
            "Xanthomonas oryzae"))

        diseaseList.add(DiseaseData("Bacterial leaf streak",R.drawable.cropde,
            "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
            "Rice,Jute","Antibiotics, Agrimycin 100, Agrimycin 500, Agric",
            "(Agrimycin)",
            "The cultivation of rice begins by planting water-soaked seeds in a properly",
            "Xanthomonas oryzae"))

        diseaseList.add(DiseaseData("Dh",R.drawable.developapp,
            "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
            "Rice,Jute","Antibiotics, Agrimycin 100, Agrimycin 500, Agric",
            "(Agrimycin)",
            "The cultivation of rice begins by planting water-soaked seeds in a properly",
            "Xanthomonas oryzae"))

        diseaseList.add(DiseaseData("Bacterial leaf streak",R.drawable.cropde,
            "The cultivation of rice begins by planting water-soaked seeds in a properly prepared",
            "Rice,Jute","Antibiotics, Agrimycin 100, Agrimycin 500, Agric",
            "(Agrimycin)",
            "The cultivation of rice begins by planting water-soaked seeds in a properly",
            "Xanthomonas oryzae"))

        diseaseAdapter.setData(diseaseList)
        setUpDisease()

    }

    private fun setUpDisease() {
        disease_recyclerview.layoutManager = StaggeredGridLayoutManager(2,
            StaggeredGridLayoutManager.VERTICAL)
        disease_recyclerview.setHasFixedSize(true)
        disease_recyclerview.adapter = diseaseAdapter
    }

    override fun onClick(item: DiseaseData, position: Int) {
        val objects = DiseaseDetailsFragment()
        objects.show(supportFragmentManager,"objects")
        val newBundle = Bundle()
        newBundle.putString("name", item.diseaseName)
        newBundle.putString("dGroupName", item.diseaseGName)
        newBundle.putString("dDetails", item.diseaseDetails)
        newBundle.putString("dCrops", item.diseaseCrop)
        newBundle.putString("dMedicine", item.diseaseMedicine)
        newBundle.putString("dMedicineRule", item.diseaseMedicineRule)
        newBundle.putString("dMedicineGroupName", item.diseaseMedicineGroupName)
        newBundle.putInt("image", item.diseaseImage)
        objects.arguments = newBundle
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        val menuItem = menu!!.findItem(R.id.search_item)
        val searchView = menuItem.actionView as SearchView
        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                diseaseAdapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                diseaseAdapter.filter.filter(newText)
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