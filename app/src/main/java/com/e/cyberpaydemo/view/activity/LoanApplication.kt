package com.e.cyberpaydemo.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.e.cyberpaydemo.R
import kotlinx.android.synthetic.main.fragment_load_application.*

class LoanApplication : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_load_application)


        nav_btn.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    DashBoard::class.java
                )
            )
        }
    }
}