package com.ddev.chasabad_adigitalfarmer.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.model.user.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var dbRef: DocumentReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_sign_up)

        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()


        signup_btn.setOnClickListener {
            val userName: String = username_edit.text.toString().trim()
            val email: String = email_edit.text.toString().trim()
            val password: String = password_edit.text.toString().trim()
            val occupation: String = occupation_edit.text.toString().trim()
            val mobile: String = phone_edit.text.toString().trim()
            if (userName.isEmpty()) {
                username_edit.error = "Please enter your name"
                username_edit.requestFocus()
            } else if (email.isEmpty()) {
                email_edit.error = "Please enter your email"
                email_edit.requestFocus()
            } else if (password.isEmpty()) {
                password_edit.error = "Please enter your password"
                password_edit.requestFocus()
            } else if (occupation.isEmpty()) {
                occupation_edit.error = "Please enter your password"
                occupation_edit.requestFocus()
            }else if (mobile.isEmpty()) {
                phone_edit.error = "Please enter your password"
                phone_edit.requestFocus()
            } else if (userName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && occupation.isNotEmpty() && mobile.isNotEmpty()) {
                signUpUser(userName, email, password, occupation, mobile)
            }
        }

        signin_btn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signUpUser(userName: String, email: String, password: String, occupation: String, mobile: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { it ->
                if (it.isSuccessful) {
                    val uId: String = mAuth.uid.toString()
                    val image: String = "image"
                    val userData = UserData(userName, uId, email, password, occupation, mobile)

                    dbRef = db.collection("UserData").document(uId)
                    dbRef.set(userData).addOnCompleteListener {
                        Log.d("details saved in db", "$it")
                    }

//                        .add(userData)
//                        .addOnSuccessListener { data ->
//                            Log.d("details saved in db", "$data")
//                        }
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "account created", Toast.LENGTH_SHORT).show()
                    Log.d("account created", it.result.toString())
                } else {
                    Log.d("account not created", it.exception.toString())
                }
            }
    }
}