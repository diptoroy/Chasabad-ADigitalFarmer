package com.ddev.chasabad_adigitalfarmer.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.question.QuestionData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var ref: FirebaseFirestore
    private lateinit var docSnap: DocumentReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        ref = FirebaseFirestore.getInstance()

        val UID = mAuth.currentUser.uid

        docSnap = ref.collection("UserData").document(UID)
//        docSnap.collection("Question").addSnapshotListener{ querySnapshot: QuerySnapshot?, firebaseFirestoreException: FirebaseFirestoreException? ->
//
//            if (querySnapshot != null) {
////                for (doc: DocumentChange in querySnapshot.documentChanges){
////                    val questionData: QuestionData = doc.document.toObject(QuestionData::class.java)
////                    questionList.add(questionData)
////                    questionAdapter.setData(questionList)
////                }
//                name_text.text = querySnapshot.query.toString()
//
//            }
        docSnap.get()
            .addOnCompleteListener {
                if (it.result.exists()) {
                    val name = it.result.getString("userName").toString()
                    val email = it.result.getString("email").toString()
                    val occupation = it.result.getString("occupation").toString()
                    val phone = it.result.getString("phone").toString()

                    name_text.text = name
                    email_text.text = email
                    occupation_text.text = occupation
                    phone_text.text = phone

                    Log.d("profile","$name")
                } else {

                }
            }
    }
}