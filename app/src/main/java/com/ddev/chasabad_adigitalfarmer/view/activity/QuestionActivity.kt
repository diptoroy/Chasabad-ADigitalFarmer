package com.ddev.chasabad_adigitalfarmer.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.question.QuestionData
import com.ddev.chasabad_adigitalfarmer.util.clickListener.QuestionOnClickListener
import com.ddev.chasabad_adigitalfarmer.view.adapter.QuestionAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.activity_crop.*
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity(),QuestionOnClickListener {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private val questionAdapter by lazy { QuestionAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        setSupportActionBar(toolbar_question)
        supportActionBar?.apply {
            val bundle:Bundle? = intent.extras
//            toolbar_question.title = bundle!!.getString("name")
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        db = FirebaseFirestore.getInstance()
        val questionList = ArrayList<QuestionData>()

        question_details_btn.setOnClickListener {
            val i = Intent(this,QuestionAddActivity::class.java)
            startActivity(i)
        }

        db.collection("Question").addSnapshotListener{ querySnapshot: QuerySnapshot?, firebaseFirestoreException: FirebaseFirestoreException? ->

            if (querySnapshot != null) {
                for (doc: DocumentChange in querySnapshot.documentChanges){
                    val questionData: QuestionData = doc.document.toObject(QuestionData::class.java)
                    questionList.add(questionData)
                    questionAdapter.setData(questionList)
                }

            }
        }
        setUpQuestion()
    }

    private fun setUpQuestion() {
        question_recyclerview.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        question_recyclerview.setHasFixedSize(true)
        question_recyclerview.adapter = questionAdapter
    }

    override fun onClick(item: QuestionData, position: Int) {
        val intent = Intent(this,QuestionAddActivity::class.java)
        intent.putExtra("question",item.question)
        intent.putExtra("answer",item.answer)
        intent.putExtra("questionId",item.questionId)
//        intent.putExtra("image",item.questionImage)
        startActivity(intent)
        Toast.makeText(this,"$position",Toast.LENGTH_SHORT).show()
        Log.d("clicked","$position")
        Log.d("questionId","${item.questionId}")
    }


}