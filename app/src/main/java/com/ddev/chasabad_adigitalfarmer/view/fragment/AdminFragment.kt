package com.ddev.chasabad_adigitalfarmer.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.ddev.chasabad_adigitalfarmer.R
import com.ddev.chasabad_adigitalfarmer.view.adapter.AdminPagerAdapter
import com.ddev.chasabad_adigitalfarmer.view.adapter.ProfileQuestionAdapter
import com.denzcoskun.imageslider.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_admin.*

class AdminFragment : Fragment(R.layout.fragment_admin) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewPager()
    }

    private fun setUpViewPager() {
        val adapter = AdminPagerAdapter(childFragmentManager)
        adapter.addFragment(EventFragment(),"Event")
        adapter.addFragment(TipsFragment(),"Tips")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }
}