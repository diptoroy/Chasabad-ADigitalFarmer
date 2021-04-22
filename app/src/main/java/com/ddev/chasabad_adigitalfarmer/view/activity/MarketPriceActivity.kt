package com.ddev.chasabad_adigitalfarmer.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.marketPrice.MarketPriceData
import com.ddev.chasabad_adigitalfarmer.model.tips.TipsData
import com.ddev.chasabad_adigitalfarmer.view.adapter.MarketPriceAdapter
import kotlinx.android.synthetic.main.activity_crop.*
import kotlinx.android.synthetic.main.activity_market_price.*
import kotlinx.android.synthetic.main.fragment_main.*

class MarketPriceActivity : AppCompatActivity() {

    private val marketPriceAdapter by lazy { MarketPriceAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_price)

        setSupportActionBar(toolbar2)
        supportActionBar?.apply {
            val bundle:Bundle? = intent.extras
            toolbar2.title = bundle!!.getString("name")
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        setupMarketPrice()
    }

    private fun setupMarketPrice() {
        market_price_ecyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        market_price_ecyclerView.setHasFixedSize(true)
        val market_price_list = ArrayList<MarketPriceData>()
        market_price_list.add(MarketPriceData("Corn","22$","21$","23$","18$"))
        market_price_list.add(MarketPriceData("Corn","22$","25$","20$","18$"))
        market_price_list.add(MarketPriceData("Corn","22$","4$","5$","5$"))
        market_price_list.add(MarketPriceData("Corn","22$","21$","33$","18$"))
        market_price_list.add(MarketPriceData("Corn","22$","21$","20$","18$"))
        market_price_list.add(MarketPriceData("Corn","22$","40$","20$","32$"))
        market_price_list.add(MarketPriceData("Corn","22$","21$","22$","18$"))
        marketPriceAdapter.setData(market_price_list)
        market_price_ecyclerView.adapter = marketPriceAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        val menuItem = menu!!.findItem(R.id.search_item)
        val searchView = menuItem.actionView as SearchView
        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                marketPriceAdapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                marketPriceAdapter.filter.filter(newText)
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