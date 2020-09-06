package com.e.cyberpaydemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.e.cyberpaydemo.api.ResultList
import com.e.cyberpaydemo.model.ApprovalModel
import com.e.cyberpaydemo.repository.LoanApprovedRepository

class LoanApprovalViewModel(application: Application) : AndroidViewModel(application) {

    val mApplication=application;
    private var  mediatorLiveData = MediatorLiveData<ResultList<ApprovalModel>>()
   var liveData: LiveData<ResultList<ApprovalModel>>?=null

    init {
        liveData= mediatorLiveData
        mediatorLiveData.addSource(liveData!!){values:ResultList<ApprovalModel>?-> mediatorLiveData.value=values}
    }

    fun fetchLoan(loanStatus:String){
        mediatorLiveData.removeSource(liveData!!)
        liveData = LoanApprovedRepository(mApplication.applicationContext).fetchLoanApproved(loanStatus)
        mediatorLiveData.addSource(liveData!!){values:ResultList<ApprovalModel>?-> mediatorLiveData.value=values}

    }

    fun getMediatorLiveData():MediatorLiveData<ResultList<ApprovalModel>>?{
        return mediatorLiveData
    }
}