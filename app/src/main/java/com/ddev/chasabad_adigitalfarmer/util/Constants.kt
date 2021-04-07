package com.ddev.chasabad_adigitalfarmer.util

class Constants {
    companion object{
        //Location
        const val BASE_URL = "http://api.openweathermap.org/"
        const val APP_ID = "1af5922fab1bb3d30821d6bb74d6bf4e"
        const val LAT = "25.743893"
        const val LON = "89.275230"
        const val REQUEST_PERMISSION_REQUEST_CODE = 2021

        //Notification
        val CHANNEL_ID = "channelID"
        val CHANNEL_NAME = "channelName"
        val NOTIFICATION_ID = 0

        val uvIndex: Double = 7.99

        //Article
        const val NEWS_BASE_URL = "https://newsapi.org/"
        const val NEWS_APP_ID = "44c8bbad59834d47ba7dd84f759e9086"

        const val FAKE_URL = "https://gist.githubusercontent.com/"
    }
}


//Full link http://api.openweathermap.org/data/2.5/weather?lat=25.743893&lon=89.275230&appid=1af5922fab1bb3d30821d6bb74d6bf4e