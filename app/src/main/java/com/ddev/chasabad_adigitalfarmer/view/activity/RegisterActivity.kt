package com.ddev.chasabad_adigitalfarmer.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.view.fragment.SignInFragment
import com.ddev.chasabad_adigitalfarmer.view.fragment.SignUpFragment

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val signUpFragment = SignUpFragment()
        val signInFragment = SignInFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container,signInFragment)
            commit()
        }
    }
}