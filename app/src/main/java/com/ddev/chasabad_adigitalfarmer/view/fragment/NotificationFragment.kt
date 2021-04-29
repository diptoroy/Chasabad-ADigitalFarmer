package com.ddev.chasabad_adigitalfarmer.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.notification.NotificationData
import com.ddev.chasabad_adigitalfarmer.model.notification.PushNotification
import com.ddev.chasabad_adigitalfarmer.network.ApiClient
import com.ddev.chasabad_adigitalfarmer.util.Constants.Companion.NOTIFICATION_TOPIC
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_notification.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotificationFragment : Fragment(R.layout.fragment_notification) {

    private val TAG: String = "NotificationFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirebaseMessaging.getInstance().subscribeToTopic(NOTIFICATION_TOPIC)

        notificationSubmit_btn.setOnClickListener {
            val title: String = notificationTitle_text.text.toString().trim()
            val message: String = notificationDetails_text.text.toString().trim()

            if (title.isNotEmpty() && message.isNotEmpty()){
                PushNotification(
                    NotificationData(title,message),
                    NOTIFICATION_TOPIC
                ).also {
                    sendNotification(it)
                }
            }
        }
    }

    private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = ApiClient.notificationApi.postNotification(notification)
            if(response.isSuccessful) {
                Log.d(TAG, "Response: ${Gson().toJson(response)}")
            } else {
                Log.e(TAG, response.errorBody().toString())
            }
        } catch(e: Exception) {
            Log.e(TAG, e.toString())
        }
    }
}