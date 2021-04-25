package com.ddev.chasabad_adigitalfarmer.view.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.question.QuestionData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_question_add.*
import java.util.*

class QuestionAddActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var storageReference: StorageReference
    private lateinit var resultUri: Uri
    private val admin: String = "LTgFcxXkX5RuZMS4kms7zwRrVp72"
    private lateinit var  questionId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_add)

        val bundle:Bundle? = intent.extras
        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        storageReference = FirebaseStorage.getInstance().reference

        if (mAuth.currentUser.uid == admin){
            answer_edit_text.visibility = View.VISIBLE
        }else if(mAuth.currentUser.uid != admin){
            answer_edit_text.visibility = View.INVISIBLE
        }

        if (bundle != null){
            submit_btn.text = "Update"
            val question: String = bundle!!.getString("question").toString()
            question_edit_text.text = Editable.Factory.getInstance().newEditable(question)
            val answer: String = bundle!!.getString("answer").toString()
            answer_edit_text.text = Editable.Factory.getInstance().newEditable(answer)
            questionId = bundle!!.getString("questionId").toString()
//            val qImage: String = bundle!!.getString("image").toString()
//            Glide.with(this).load(bundle!!.getString(qImage)).into(question_image);
        }else{
            submit_btn.text = "Submit"
        }

        submit_btn.setOnClickListener {
            val question: String = question_edit_text.text.toString().trim()
            val answer: String = answer_edit_text.text.toString().trim()
            if (bundle != null){
                delete_btn.visibility = View.VISIBLE
                val qId: String = questionId
                Log.d("question id is","$qId")
                updateQuestion(qId, question, answer)
            }else{
                delete_btn.visibility = View.INVISIBLE
                val qId: String = UUID.randomUUID().toString()
                addQuestion(qId,question, answer)
            }
        }

        delete_btn.setOnClickListener {
            deleteQuestion(questionId)
        }


    }

    private fun addQuestion(qId: String, question: String, answer: String) {
        val userId: String = mAuth.currentUser.uid
        progressBar2.visibility = View.VISIBLE
        val time = System.currentTimeMillis()

        val questionData = QuestionData(question, answer, userId, time, qId)

        db.collection("Question").document(qId).set(questionData).addOnCompleteListener {
            if (it.isSuccessful) {
                progressBar2.visibility = View.INVISIBLE
                val intent = Intent(this, QuestionActivity::class.java)
                startActivity(intent)
                finish()
                Log.d("question", "$it")
            } else {
                progressBar2.visibility = View.VISIBLE
            }
        }
    }

    private fun deleteQuestion(questionId: String) {
        db.collection("Question").document(questionId).delete().addOnCompleteListener {
            progressBar2.visibility = View.VISIBLE
            if (it.isSuccessful){
                progressBar2.visibility = View.INVISIBLE
                val intent = Intent(this,QuestionActivity::class.java)
                startActivity(intent)
                Log.d("Deleted!","$it")
                Toast.makeText(this, "Data Updated!!", Toast.LENGTH_SHORT).show();
            }else{
                progressBar2.visibility = View.VISIBLE
                Log.d("error","${it.exception}")
            }
        }
    }

    private fun updateQuestion(qId: String, question: String, answer: String) {
        progressBar2.visibility = View.VISIBLE
        db.collection("Question").document(qId)
            .update("question",question,"answer",answer)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    progressBar2.visibility = View.INVISIBLE
                    val intent = Intent(this,QuestionActivity::class.java)
                    startActivity(intent)
                    Log.d("updated!","$it")
                    Toast.makeText(this, "Data Updated!!", Toast.LENGTH_SHORT).show();
                    finish()
                }else{
                    progressBar2.visibility = View.VISIBLE
                    Log.d("error","${it.exception}")
                }
            }

    }
}