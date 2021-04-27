package com.ddev.chasabad_adigitalfarmer.view.fragment

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.event.EventData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_question_add.*
import kotlinx.android.synthetic.main.fragment_event.*
import java.util.*


class EventFragment : Fragment(R.layout.fragment_event) {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var  eventId: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()



        eventsubmit_btn.setOnClickListener {
            val eName: String = eventname_text.text.toString().trim()
            val eDate: String = eventdate_text.text.toString().trim()
            val eMonth: String = eventmonth_text.text.toString().trim()
            val eTime: String = eventtime_text.text.toString().trim()
            val eLocation: String = eventlocation_text.text.toString().trim()
            val eAuthor: String = eventauthor_text.text.toString().trim()

            if (eName.isEmpty()) {
                eventname_text.error = "Please enter Event name"
                eventname_text.requestFocus()
            }else if (eDate.isEmpty()) {
                eventdate_text.error = "Please enter date"
                eventdate_text.requestFocus()
            }else if (eMonth.isEmpty()) {
                eventmonth_text.error = "Please enter month"
                eventmonth_text.requestFocus()
            }else if (eTime.isEmpty()) {
                eventtime_text.error = "Please enter time"
                eventtime_text.requestFocus()
            }else if (eLocation.isEmpty()) {
                eventlocation_text.error = "Please enter location"
                eventlocation_text.requestFocus()
            }else if (eAuthor.isEmpty()) {
                eventauthor_text.error = "Please enter Author"
                eventauthor_text.requestFocus()
            }else{
                val eId: String = UUID.randomUUID().toString()
                addEvent(eName, eDate, eMonth, eTime, eLocation, eAuthor, eId)
            }

        }


    }

    private fun addEvent(
        eName: String,
        eDate: String,
        eMonth: String,
        eTime: String,
        eLocation: String,
        eAuthor: String,
        eId: String
    ) {
        val time = System.currentTimeMillis()
        val eventData = EventData(eName, eDate, eMonth, eTime, eLocation, eAuthor, eId,time)
        event_progressBar.visibility = View.VISIBLE
        db.collection("Event").document(eId).set(eventData).addOnCompleteListener {
            if (it.isSuccessful) {
                event_progressBar.visibility = View.INVISIBLE
                eventname_text.text = Editable.Factory.getInstance().newEditable("")
                eventdate_text.text = Editable.Factory.getInstance().newEditable("")
                eventmonth_text.text = Editable.Factory.getInstance().newEditable("")
                eventtime_text.text = Editable.Factory.getInstance().newEditable("")
                eventlocation_text.text = Editable.Factory.getInstance().newEditable("")
                eventauthor_text.text = Editable.Factory.getInstance().newEditable("")
                Log.d("event", "$it")
            } else {
                event_progressBar.visibility = View.VISIBLE
            }
        }

    }


}