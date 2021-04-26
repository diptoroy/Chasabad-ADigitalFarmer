package com.ddev.chasabad_adigitalfarmer.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.util.darkMode.DataStoreClass
import com.ddev.chasabad_adigitalfarmer.util.darkMode.UiMode
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.coroutines.launch


class SettingFragment : Fragment(R.layout.fragment_setting) {
    //DataStore
    private lateinit var dataStoreClass: DataStoreClass

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //DataStore
        dataStoreClass = context?.let { DataStoreClass(it) }!!
        observUi()
        initView()
    }

    private fun observUi() {
        dataStoreClass.uiModeFlow.asLiveData().observe(viewLifecycleOwner) { uiMode ->
            setCheckedMode(uiMode)
        }
    }

    private fun setCheckedMode(uiMode: UiMode?) {
        when (uiMode) {
            UiMode.LIGHT -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switch_theme.isChecked = false
            }
            UiMode.DARK -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switch_theme.isChecked = true
            }
        }
    }

    private fun initView() {
        switch_theme.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                when (isChecked) {
                    true -> dataStoreClass.setDarkMode(UiMode.DARK)
                    false -> dataStoreClass.setDarkMode(UiMode.LIGHT)
                }
            }
        }
    }
}