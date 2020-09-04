package com.e.cyberpaydemo.view.fragment.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.e.cyberpaydemo.R


class LoanApproved : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loan_approved, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            LoanApproved().apply {
                arguments = Bundle().apply {

                }
            }
    }
}