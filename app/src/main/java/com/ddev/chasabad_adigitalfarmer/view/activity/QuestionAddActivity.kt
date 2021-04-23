package com.ddev.chasabad_adigitalfarmer.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.question.QuestionData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_question_add.*

class QuestionAddActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_add)

        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        submit_btn.setOnClickListener {
            val question: String = question_edit_text.text.toString().trim()
            val answer: String = answer_edit_text.text.toString().trim()
            addQuestion(question, answer)
        }
    }

    private fun addQuestion(question: String, answer: String) {
        val userId: String = mAuth.currentUser.uid
        progressBar2.visibility = View.VISIBLE
        val time = System.currentTimeMillis()

        val questionData = QuestionData(question, answer, userId, time)
        db.collection("question").add(questionData).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                progressBar2.visibility = View.INVISIBLE
                val intent = Intent(this, QuestionActivity::class.java)
                startActivity(intent)
                Log.d("question", "$task")
            } else {
                progressBar2.visibility = View.VISIBLE
            }
        }
    }
}