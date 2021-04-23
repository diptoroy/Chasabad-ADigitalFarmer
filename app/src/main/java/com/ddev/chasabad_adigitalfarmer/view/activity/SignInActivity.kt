package com.ddev.chasabad_adigitalfarmer.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ddev.chasabad_adigitalfarmer.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_sign_in)

        mAuth = FirebaseAuth.getInstance()

        signin_btn_signin.setOnClickListener {
            val email: String = email_edit_signin.text.toString().trim()
            val password: String = password_edit_signin.text.toString().trim()
            signInUser(email,password)
        }

        signup_btn_signin.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signInUser(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "login", Toast.LENGTH_SHORT).show()
                    Log.d("login", it.result.toString())
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Log.d("login failed", it.exception.toString())
                }
            }

    }
}