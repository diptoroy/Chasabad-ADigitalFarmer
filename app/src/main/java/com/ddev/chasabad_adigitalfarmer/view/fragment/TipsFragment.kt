package com.ddev.chasabad_adigitalfarmer.view.fragment

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.event.EventData
import com.ddev.chasabad_adigitalfarmer.model.tips.TipsData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_event.*
import kotlinx.android.synthetic.main.fragment_tips.*
import java.util.*


class TipsFragment : Fragment(R.layout.fragment_tips) {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        tipssubmit_btn.setOnClickListener {
            val tName: String = tipstitle_text.text.toString().trim()
            val tDetails: String = tipsDetails_text.text.toString().trim()

            if (tName.isEmpty()) {
                tipstitle_text.error = "Please enter Event name"
                tipstitle_text.requestFocus()
            }else if (tDetails.isEmpty()) {
                tipsDetails_text.error = "Please enter date"
                tipsDetails_text.requestFocus()
            }else{
                val tId: String = UUID.randomUUID().toString()
                addTips(tName,tDetails,tId)
            }
        }
    }

    private fun addTips(tName: String, tDetails: String, tId: String) {
        val time = System.currentTimeMillis()
        val tipsData = TipsData(tName,tDetails,tId,time)
        tips_progressBar.visibility = View.VISIBLE
        db.collection("Tips").add(tipsData).addOnCompleteListener {
            if (it.isSuccessful) {
                tips_progressBar.visibility = View.INVISIBLE
                tipstitle_text.text = Editable.Factory.getInstance().newEditable("")
                tipsDetails_text.text = Editable.Factory.getInstance().newEditable("")
                Log.d("tips", "$it")
            } else {
                tips_progressBar.visibility = View.VISIBLE
            }
        }
    }


}