package com.e.cyberpaydemo.view.fragment.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.cyberpaydemo.R
import com.e.cyberpaydemo.callback.ClickListener
import com.e.cyberpaydemo.databinding.FragmentLoanApprovedBinding
import com.e.cyberpaydemo.databinding.LoanStatusDialogBinding
import com.e.cyberpaydemo.model.LoanModel
import com.e.cyberpaydemo.view.adapter.LoanAdapter
import com.e.cyberpaydemo.viewmodel.LoanApprovalViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_loan_approved.view.*
import kotlinx.android.synthetic.main.loan_status_dialog.view.*


@AndroidEntryPoint
class LoanApproved : Fragment() {


    private var  loanApprovalViewModel:LoanApprovalViewModel?=null
    private var loanAdapter:LoanAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            loanAdapter = LoanAdapter()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentLoanApprovedBinding.inflate(inflater, container, false)
        val view = binding.root
        setContentView(view)
        return view
    }

   private fun setContentView(view: View){
/*       val loanAdapter = LoanAdapter()*/
       view.apply {
           loan_recy.apply {
               adapter = loanAdapter
               layoutManager = LinearLayoutManager(context)

           }
           loanAdapter!!.setItemClickListener(object :ClickListener<LoanModel>{
               override fun onClick(mode: LoanModel) {
                 openLoanDialog(mode)
               }
           })

       }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loanApprovalViewModel = ViewModelProvider(this)[LoanApprovalViewModel::class.java]
        loanApprovalViewModel!!.loans.observe(viewLifecycleOwner, Observer {

            result-> loanAdapter!!.submitList(result.data)
        })


    }

    private fun openLoanDialog(loanModel: LoanModel){

        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.Theme_Design_BottomSheetDialog)
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.loan_status_dialog, null)

       view.apply {
           if (loanModel.isLoanApproved) title_dialog.text = "Loan Approved" else  title_dialog.text = "Loan Pending"
           close_btn.setOnClickListener { bottomSheetDialog.dismiss() }
       }
        bottomSheetDialog.setCanceledOnTouchOutside(false)
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
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