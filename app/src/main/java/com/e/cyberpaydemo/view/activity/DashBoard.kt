package com.e.cyberpaydemo.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.e.cyberpaydemo.R
import com.e.cyberpaydemo.view.fragment.dashboard.LoanApproved
import com.e.cyberpaydemo.view.fragment.dashboard.PendingLoan
import com.google.android.material.tabs.TabLayout

class DashBoard : AppCompatActivity() {

   var viewPager :ViewPager?=null
    var sectionViewPageAdapter:SectionViewPageAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewPager = findViewById(R.id.viewPager)
        sectionViewPageAdapter = SectionViewPageAdapter(supportFragmentManager)
        val tabLayout:TabLayout = findViewById(R.id.tabs)

        val tab1 = tabLayout.newTab()
        tab1.text = "Approved"
        tabLayout.addTab(tab1)

        val tab2 = tabLayout.newTab()
        tab2.text = "Pending"
        tabLayout.addTab(tab2)

        viewPager!!.adapter = sectionViewPageAdapter
        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewPager))

    }



    class SectionViewPageAdapter(fm:FragmentManager):FragmentPagerAdapter(fm){
        override fun getItem(position: Int): Fragment {

            return when(position){
                0->LoanApproved.newInstance()
                1->PendingLoan.newInstance()
                else->LoanApproved.newInstance()
            }
        }

        override fun getCount(): Int {

            return 3
        }

    }

}