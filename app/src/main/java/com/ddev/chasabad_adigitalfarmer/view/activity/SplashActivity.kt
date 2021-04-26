package com.ddev.chasabad_adigitalfarmer.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ddev.chasabad_adigitalfarmer.R
import com.google.android.material.internal.NavigationMenu
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mAuth = FirebaseAuth.getInstance()

        val user = mAuth.currentUser
        val authListener: FirebaseAuth.AuthStateListener

        authListener = AuthStateListener { firebaseAuth ->
            if (user != null) {
                lifecycleScope.launch {
                    delay(3000)
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }else{
                lifecycleScope.launch {
                    delay(3000)
                    val intent = Intent(this@SplashActivity, SignInActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        mAuth.addAuthStateListener(authListener)
    }

    override fun onStart() {
        super.onStart()


//        if (user != null) {
//            lifecycleScope.launch {
//                delay(3000)
//                val intent = Intent(this@SplashActivity, MainActivity::class.java)
//                startActivity(intent)
//                finish()
//            }
//        } else {
//            lifecycleScope.launch {
//                delay(3000)
//                val intent = Intent(this@SplashActivity, SignInActivity::class.java)
//                startActivity(intent)
//                finish()
//            }
//        }
    }
}