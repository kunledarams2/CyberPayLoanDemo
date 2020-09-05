package com.e.cyberpaydemo.view.fragment.dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.cyberpaydemo.R
import com.e.cyberpaydemo.model.ApprovalModel
import com.e.cyberpaydemo.view.adapter.LoanApprovalAdapter
import com.e.cyberpaydemo.viewmodel.LoanApprovalViewModel
import kotlinx.android.synthetic.main.fragment_loan_approved.*
import kotlinx.android.synthetic.main.fragment_loan_approved.view.*


class LoanApproved : Fragment() {

    private var  loanApprovalViewModel:LoanApprovalViewModel?=null
    private var loanApprovalAdapter:LoanApprovalAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            loanApprovalAdapter = LoanApprovalAdapter()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_loan_approved, container, false)
        setContentView(view)
        return view
    }

   private fun setContentView(view: View){
       val llm = LinearLayoutManager(context)
       view.loan_recy.layoutManager = llm
       view.loan_recy.adapter = loanApprovalAdapter

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loanApprovalViewModel = ViewModelProviders.of(this)[LoanApprovalViewModel::class.java]
        observeLoadVM(loanApprovalViewModel!!)

    }

    private fun observeLoadVM(loanApprovalViewModel: LoanApprovalViewModel){
        loanApprovalViewModel.getMediatorLiveData()!!.observe(this, Observer {
            result->
            Log.d("Inside","Hello ${result.getDataList()}")
            if (!result.getDataList()!!.isNullOrEmpty()){
                loanApprovalAdapter!!.setData(result.getDataList() as  ArrayList<ApprovalModel>)

            }
//            if (result.isNotEmpty()){
//
//
//            }
        })
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