package com.ddev.chasabad_adigitalfarmer.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.marketPrice.MarketPriceData
import com.ddev.chasabad_adigitalfarmer.model.tips.TipsData
import com.ddev.chasabad_adigitalfarmer.view.adapter.MarketPriceAdapter
import kotlinx.android.synthetic.main.activity_market_price.*
import kotlinx.android.synthetic.main.fragment_main.*

class MarketPriceActivity : AppCompatActivity() {

    private val marketPriceAdapter by lazy { MarketPriceAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_price)

        setupMarketPrice()
    }

    private fun setupMarketPrice() {
        market_price_ecyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        market_price_ecyclerView.setHasFixedSize(true)
        val market_price_list = ArrayList<MarketPriceData>()
        market_price_list.add(MarketPriceData("Corn","22$","21$","20$","18$"))
        market_price_list.add(MarketPriceData("Rice","23.5$","23$","29$","30$"))
        market_price_list.add(MarketPriceData("Corn","22$","21$","20$","18$"))
        market_price_list.add(MarketPriceData("Rice","23.5$","23$","29$","30$"))
        market_price_list.add(MarketPriceData("Corn","22$","21$","20$","18$"))
        market_price_list.add(MarketPriceData("Rice","23.5$","23$","29$","30$"))
        market_price_list.add(MarketPriceData("Corn","22$","21$","20$","18$"))
        market_price_list.add(MarketPriceData("Rice","23.5$","23$","29$","30$"))

        marketPriceAdapter.setData(market_price_list)
        market_price_ecyclerView.adapter = marketPriceAdapter
    }
}