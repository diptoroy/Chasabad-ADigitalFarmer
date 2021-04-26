package com.ddev.chasabad_adigitalfarmer.view.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.question.QuestionData
import com.ddev.chasabad_adigitalfarmer.view.activity.SignInActivity
import com.ddev.chasabad_adigitalfarmer.view.adapter.ProfileQuestionAdapter
import com.ddev.chasabad_adigitalfarmer.view.adapter.QuestionAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_article_details.*
import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var ref: FirebaseFirestore
    private lateinit var docSnap: DocumentReference
    private lateinit var storageReference: StorageReference
    private  var resultUri: Uri? = null
    private val pQuestionAdapter by lazy { ProfileQuestionAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        ref = FirebaseFirestore.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        val questionList = ArrayList<QuestionData>()
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

        ref.collection("Question").whereEqualTo("userId",UID)
            .orderBy("time",Query.Direction.DESCENDING)
            .addSnapshotListener { querySnapshot: QuerySnapshot?, firebaseFirestoreException: FirebaseFirestoreException? ->
                if (querySnapshot != null) {
                    for (doc: DocumentChange in querySnapshot.documentChanges){
                        val questionData: QuestionData = doc.document.toObject(QuestionData::class.java)
                        questionList.add(questionData)
                        pQuestionAdapter.setData(questionList)
                        Log.d("Question List","$questionData")
                    }

                }
            }

        //log out btn
        logout_btn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(context,SignInActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }


        //profile image
            profile_image.setOnClickListener {
                context?.let { it1 ->
                    CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setMinCropResultSize(512, 512)
                        .setAspectRatio(1, 1)
                        .start(it1,this)
                };
            }


        var imagePath: StorageReference =
            storageReference.child("profile_image").child("$UID.jpg")

        resultUri?.let {
            imagePath.putFile(it).addOnCompleteListener { image ->
                if (image.isSuccessful) {
                    val downloadUri = image.result
                    val imagePath: String = downloadUri.toString()
                    Glide.with(this).load(imagePath).into(profile_image);
                }
            }
        }

        setUpQuestion()

    }

    private fun setUpQuestion() {
        profile_question_recyclerview.layoutManager = LinearLayoutManager(activity,
            LinearLayoutManager.VERTICAL,false)
        profile_question_recyclerview.setHasFixedSize(true)
        profile_question_recyclerview.adapter = pQuestionAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == AppCompatActivity.RESULT_OK) {
                resultUri = result.uri
                var bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver,resultUri)
                profile_image.setImageBitmap(bitmap)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
            }
        }
    }
}