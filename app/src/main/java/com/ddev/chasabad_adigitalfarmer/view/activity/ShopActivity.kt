package com.ddev.chasabad_adigitalfarmer.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.shop.ShopData
import com.ddev.chasabad_adigitalfarmer.util.ShopOnItemClickListener
import com.ddev.chasabad_adigitalfarmer.view.adapter.ShopAdapter
import com.ddev.chasabad_adigitalfarmer.view.fragment.ShopDetailsFragment
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import kotlinx.android.synthetic.main.activity_shop.*

class ShopActivity : AppCompatActivity(),ShopOnItemClickListener {

    private val sliderList = ArrayList<SlideModel>()
    private val shopAdapter by lazy { ShopAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        sliderList.add(SlideModel(R.drawable.developapp))
        sliderList.add(SlideModel(R.drawable.developapp))
        sliderList.add(SlideModel(R.drawable.developapp))

        val imageSlider = findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(sliderList)

        setupShopRecyclerView()
    }

    private fun setupShopRecyclerView() {
        shop_recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        shop_recyclerView.setHasFixedSize(true)
        val shopList = ArrayList<ShopData>()
        shopList.add(
            ShopData(
                R.drawable.tractor, "Tata Tractor", "4.2", "$12000", "It,s a tata tractor",
                "Jhon Doe", "NewYork, USA", "+983256788", "Tata Agro", "www.tatamotors.com"
            )
        )
        shopList.add(
            ShopData(
                R.drawable.tractor, "Ford Tractor", "4.2", "$12000", "It,s a tata tractor",
                "Jhon Doe", "NewYork, USA", "+983256788", "Tata Agro", "www.tatamotors.com"
            )
        )
        shopList.add(
            ShopData(
                R.drawable.developapp, "BMW Tractor", "4.2", "$12000", "It,s a tata tractor",
                "Jhon Doe", "NewYork, USA", "+983256788", "Tata Agro", "www.tatamotors.com"
            )
        )
        shopAdapter.setData(shopList)
        shop_recyclerView.adapter = shopAdapter
    }

    override fun onClick(item: ShopData, position: Int) {
        val objects = ShopDetailsFragment()
        objects.show(supportFragmentManager,"objects")
        val newBundle = Bundle()
        newBundle.putString("name", item.productName)
        newBundle.putString("image", item.productImage.toString())
        newBundle.putString("price", item.productPrice)
        newBundle.putString("details", item.productDetails)
        newBundle.putString("store", item.productStore)
        newBundle.putString("author", item.productAuthor)
        newBundle.putString("location", item.productShopLocation)
        newBundle.putString("phone", item.productPhone)
        newBundle.putString("site", item.productSite)
        objects.arguments = newBundle
    }

}

