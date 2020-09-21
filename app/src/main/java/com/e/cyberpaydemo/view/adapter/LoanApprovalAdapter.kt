package com.e.cyberpaydemo.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.cyberpaydemo.R
import com.e.cyberpaydemo.model.ApprovalModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.approval_holder.view.*

class LoanApprovalAdapter():RecyclerView.Adapter<LoanApprovalAdapter.VHClass>() {

    private var loanApprovedList=ArrayList<ApprovalModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanApprovalAdapter.VHClass {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.approval_holder,parent, false)
        return VHClass(view)

    }

    override fun getItemCount(): Int {
        return  loanApprovedList.size
    }

    fun setData(mLoanList:ArrayList<ApprovalModel>){
        this.loanApprovedList = mLoanList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: LoanApprovalAdapter.VHClass, position: Int) {

        holder.bindView(loanApprovedList[position])
    }

    class VHClass(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindView(approvalModel: ApprovalModel){

                    itemView.staff_name.text = approvalModel.name
                    itemView.time.text= approvalModel.time
                    itemView.amount.text= "N${approvalModel.amount}"
                    Picasso.get().load(approvalModel.avatar).into(itemView.circleImageView)



        }
    }
}