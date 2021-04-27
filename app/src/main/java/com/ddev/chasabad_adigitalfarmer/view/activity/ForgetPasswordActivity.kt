package com.ddev.chasabad_adigitalfarmer.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ddev.chasabad_adigitalfarmer.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forget_password.*
import kotlinx.android.synthetic.main.fragment_sign_in.*

class ForgetPasswordActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        mAuth = FirebaseAuth.getInstance()

        forgetPass_btn_forget.setOnClickListener {
            val email: String = forget_email_edit_signin.text.toString().trim()
            if (email.isEmpty()){
                forget_email_edit_signin.error = "Please provide your valid email"
                forget_email_edit_signin.requestFocus()
            }else{
                forgetPassword(email)
            }

        }
    }

    private fun forgetPassword(email: String) {
        forget_progressBar.visibility = View.VISIBLE
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener {
            if (it.isSuccessful){
                val intent = Intent(this,SignInActivity::class.java)
                startActivity(intent)
                forget_progressBar.visibility = View.INVISIBLE
            }else{
                forget_progressBar.visibility = View.VISIBLE
            }
        }
    }
}