package com.e.cyberpaydemo.view.fragment.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.cyberpaydemo.R
import com.e.cyberpaydemo.viewmodel.LoanApprovalViewModel
import kotlinx.android.synthetic.main.fragment_loan_approved.view.*


class PendingLoan : Fragment() {

    private var  loanApprovalViewModel:LoanApprovalViewModel?=null


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
         val view = inflater.inflate(R.layout.fragment_pending_loan, container, false)
        setContentView(view)
        return view
    }


    private fun setContentView(view: View){
        val llm = LinearLayoutManager(context)
        view.loan_recy.layoutManager = llm


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        loanApprovalViewModel = ViewModelProviders.of(this)[LoanApprovalViewModel::class.java]
        /*loanApprovalViewModel!!.fetchLoan("false")
        observeLoadVM(loanApprovalViewModel!!)*/

    }

 /*   private fun observeLoadVM(loanApprovalViewModel: LoanApprovalViewModel){
        loanApprovalViewModel.getMediatorLiveData()!!.observe(this, Observer {
                result->
            Log.d("Inside","Hello ${result.getDataList()}")
            if (!result.getDataList()!!.isNullOrEmpty()){
                loanApprovalAdapter!!.setData(result.getDataList() as  ArrayList<ApprovalModel>)

            }

        })
   } */

    companion object {

        @JvmStatic
        fun newInstance() =
            PendingLoan().apply {
                arguments = Bundle().apply {

                }
            }
    }
}