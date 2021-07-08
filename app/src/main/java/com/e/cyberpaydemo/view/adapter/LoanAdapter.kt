package com.e.cyberpaydemo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.e.cyberpaydemo.callback.ClickListener

import com.e.cyberpaydemo.databinding.ApprovalHolderBinding
import com.e.cyberpaydemo.model.LoanModel
import com.squareup.picasso.Picasso

class LoanAdapter : ListAdapter<LoanModel, LoanAdapter.LoanViewHolder>(LoanComparator()) {

   var clickListener : ClickListener<LoanModel>?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanAdapter.LoanViewHolder {
        val binding =
            ApprovalHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoanAdapter.LoanViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bindView(currentItem)
        }
    }

    fun setItemClickListener(clickListener: ClickListener<LoanModel>){
        this.clickListener=clickListener
    }

   inner class LoanViewHolder(private val binding: ApprovalHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(loanModel: LoanModel) {
            binding.apply {
//                Picasso.get().load(loanModel.avatar).into(circleImageView)
                staffName.text = loanModel.name
                amount.text = "N${loanModel.amount}"
                time.text = loanModel.createdAt
                root.setOnClickListener {
                    clickListener!!.onClick(loanModel)
                }
            }

        }


    }

    class LoanComparator : DiffUtil.ItemCallback<LoanModel>() {
        override fun areItemsTheSame(oldItem: LoanModel, newItem: LoanModel) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: LoanModel, newItem: LoanModel) =
            oldItem == newItem

    }
}