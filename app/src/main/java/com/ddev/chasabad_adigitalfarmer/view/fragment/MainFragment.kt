package com.ddev.chasabad_adigitalfarmer.view.fragment

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ddev.chasabad_adigitalfarmer.view.viewmodel.MainFragmentViewModel
import com.ddev.chasabad_adigitalfarmer.view.viewmodel.MainFragmentViewModelFactory
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.event.EventData
import com.ddev.chasabad_adigitalfarmer.model.tips.TipsData
import com.ddev.chasabad_adigitalfarmer.model.MenuData
import com.ddev.chasabad_adigitalfarmer.repository.Repository
import com.ddev.chasabad_adigitalfarmer.util.Constants
import com.ddev.chasabad_adigitalfarmer.util.Constants.Companion.NOTIFICATION_ID
import com.ddev.chasabad_adigitalfarmer.util.Constants.Companion.REQUEST_PERMISSION_REQUEST_CODE
import com.ddev.chasabad_adigitalfarmer.util.Constants.Companion.uvIndex
import com.ddev.chasabad_adigitalfarmer.view.adapter.EventAdapter
import com.ddev.chasabad_adigitalfarmer.view.adapter.MenuAdapter
import com.ddev.chasabad_adigitalfarmer.view.adapter.TipsAdapter
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.main_activity_up.*
import kotlinx.android.synthetic.main.risesetlayout.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainFragment : Fragment() {

    private lateinit var viewModel: MainFragmentViewModel
    private lateinit var lat: String
    private lateinit var lon: String
    private val menuAdapter by lazy { MenuAdapter() }
    private val eventAdapter by lazy { EventAdapter() }
    private val tipsAdapter by lazy { TipsAdapter() }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_main, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = Repository()
        val viewModelFactory = MainFragmentViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainFragmentViewModel::class.java)

        setupMenuRecyclerView()
        setupEventRecyclerView()
        setupTipsRecyclerView()

        if (context?.let {
                    ContextCompat.checkSelfPermission(
                            it, android.Manifest.permission.ACCESS_FINE_LOCATION
                    )
                } != PackageManager.PERMISSION_GRANTED) {
            activity?.let {
                ActivityCompat.requestPermissions(
                        it,
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_PERMISSION_REQUEST_CODE
                )
            }
        } else {
            getCurrentLocation()
        }

    }

    private fun setupTipsRecyclerView() {
        tips_recyclerview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        tips_recyclerview.setHasFixedSize(true)
        val tipsList = ArrayList<TipsData>()
        tipsList.add(TipsData("Agricultural Fair 2021","Agricultural Fair 2021, Agricultural Fair 2021, Agricultural Fair 2021"))
        tipsList.add(TipsData("Agricultural Fair 2021","Agricultural Fair 2021, Agricultural Fair 2021, Agricultural Fair 2021"))
        tipsList.add(TipsData("Agricultural Fair 2021","Agricultural Fair 2021, Agricultural Fair 2021, Agricultural Fair 2021"))
        tipsList.add(TipsData("Agricultural Fair 2021","Agricultural Fair 2021, Agricultural Fair 2021, Agricultural Fair 2021"))
        tipsAdapter.setData(tipsList)
        tips_recyclerview.adapter = tipsAdapter
    }

    private fun setupEventRecyclerView() {
        event_recyclerview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        event_recyclerview.setHasFixedSize(true)
        val eventList = ArrayList<EventData>()
        eventList.add(EventData("Agricultural Fair 2021","21","March",R.drawable.developapp,"Agricultural Fair 2021","Dr.Jhon Doe","London,England"))
        eventList.add(EventData("Agricultural Fair 2021","21","March",R.drawable.developapp,"Agricultural Fair 2021","Dr.Jhon Doe","London,England"))
        eventList.add(EventData("Agricultural Fair 2021","21","March",R.drawable.developapp,"Agricultural Fair 2021","Dr.Jhon Doe","London,England"))
        eventList.add(EventData("Agricultural Fair 2021","21","March",R.drawable.developapp,"Agricultural Fair 2021","Dr.Jhon Doe","London,England"))
        eventList.add(EventData("Agricultural Fair 2021","21","March",R.drawable.developapp,"Agricultural Fair 2021","Dr.Jhon Doe","London,England"))
        eventAdapter.setData(eventList)
        event_recyclerview.adapter = eventAdapter
    }

    private fun setupMenuRecyclerView() {
        menu_recyclerView.layoutManager =
            StaggeredGridLayoutManager(4, LinearLayoutManager.VERTICAL)
        menu_recyclerView.setHasFixedSize(true)
        val menuList = ArrayList<MenuData>()
        menuList.add(MenuData(R.drawable.plant, "Crop"))
        menuList.add(MenuData(R.drawable.plant, "Nursery"))
        menuList.add(MenuData(R.drawable.plant, "Disease"))
        menuList.add(MenuData(R.drawable.plant, "Market Price"))
        menuList.add(MenuData(R.drawable.plant, "Shop"))
        menuList.add(MenuData(R.drawable.plant, "Article"))
        menuList.add(MenuData(R.drawable.plant, "Question Bank"))
        menuList.add(MenuData(R.drawable.plant, "Others"))
        Log.d("menu", menuList.toString())
        menuAdapter.setData(menuList)
        menu_recyclerView.adapter = menuAdapter
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_REQUEST_CODE && grantResults.isNotEmpty()) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation()
            } else {
                Toast.makeText(context, "Permission Denied!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getCurrentLocation() {

        var locationRequest = LocationRequest()
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        //now getting address from latitude and longitude

        val geocoder = Geocoder(context, Locale.getDefault())
        var addresses: List<Address>

        if (context?.let {
                    ActivityCompat.checkSelfPermission(
                            it,
                            Manifest.permission.ACCESS_FINE_LOCATION
                    )
                } != PackageManager.PERMISSION_GRANTED && context?.let {
                    ActivityCompat.checkSelfPermission(
                            it,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                } != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        LocationServices.getFusedLocationProviderClient(context)
                .requestLocationUpdates(locationRequest, object : LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult?) {
                        super.onLocationResult(locationResult)
                        LocationServices.getFusedLocationProviderClient(context)
                                .removeLocationUpdates(this)
                        if (locationResult != null && locationResult.locations.size > 0) {
                            var locIndex = locationResult.locations.size - 1

                            lat = locationResult.locations.get(locIndex).latitude.toString()
                            lon = locationResult.locations.get(locIndex).longitude.toString()
//                            try {
//                                addresses = geocoder.getFromLocation(lat.toDouble(), lon.toDouble(), 1)
//                                var address: String = addresses[0].getAddressLine(0)
//                                locationText.text = address
//                                getData()
//                            }catch (e:Exception){
//                                Log.d("Dipto",e.toString())
//                            }
                            getData()
                        }
                    }
                }, Looper.getMainLooper())
    }

    private fun getData() {
        viewModel.getUviData(lat, lon, Constants.APP_ID)
        activity?.let { it ->
            viewModel.dailyDataResponse.observe(
                    it,
                    androidx.lifecycle.Observer { response ->
                        response.body()?.toString()?.let { Log.d("all", it) }
                        val temp: Double? =
                                (response.body()?.current?.temp?.minus(273.5))
                        val temperature: String = String.format("%.2f", temp)
                        val humidity: String =
                                response.body()?.current?.humidity.toString()
                        val wind: String =
                                response.body()?.current?.wind_speed.toString()
                        val pressure: String =
                                response.body()?.current?.pressure.toString()
                        val riseUnixSeconds: Int? = response.body()?.current?.sunrise
                        val setUnixSeconds: Int? = response.body()?.current?.sunset

                        val uvi: Double? = response.body()?.current?.uvi
                        val rDate: Date? =
                                riseUnixSeconds?.times(1000L)?.let { java.util.Date(it) }
                        val sDate: Date? =
                                setUnixSeconds?.times(1000L)?.let { java.util.Date(it) }
                        //get current date and time using format
                        //pattern likes "yyyy-MM-dd HH:mm"
                        val timeFormat = SimpleDateFormat("HH:mm a")
                        timeFormat.timeZone = java.util.TimeZone.getTimeZone("GMT+6");
                        val sunrise: String = timeFormat.format(rDate)
                        val sunset: String = timeFormat.format(sDate)

                        pressaure_text.text = "$pressure%"
                        wind_text.text = "$wind km/h"
                        humidity_yext.text = "$humidity%"
                        tempText.text = "$temperature ° C"
                        riseText.text = sunrise
                        setText.text = sunset
                        if (uvi != null) {
                            if (uvi > uvIndex ) {
                                createNotificationChannel()
                                val notificationBuilder = context?.let { noti -> androidx.core.app.NotificationCompat.Builder(noti, Constants.CHANNEL_ID) }
                                        ?.setContentTitle("UV Threat!")
                                        ?.setContentText("Please Stay in Home.")
                                        ?.setSmallIcon(R.drawable.uvimage)
                                        ?.setPriority(androidx.core.app.NotificationCompat.PRIORITY_HIGH)
                                        ?.build()

                                val notificationManager = context?.let { it1 -> NotificationManagerCompat.from(it1) }

                                notificationManager?.notify(NOTIFICATION_ID, notificationBuilder!!)
                            }
                        }
                    })
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT).apply {
                lightColor = Color.RED
                enableLights(true)
            }
            val manager = activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}