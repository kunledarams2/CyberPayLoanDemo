package com.e.cyberpaydemo.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.e.cyberpaydemo.api.MyVolleyRequest
import com.e.cyberpaydemo.callback.IVolleyResponse
import com.e.cyberpaydemo.model.ApprovalModel
import org.json.JSONObject

class LoanApprovedRepository(private val context: Context):IVolleyResponse {

    fun fetchLoanApproved(){
        val myVolleyRequest= MyVolleyRequest.getInstance(context, this)

        myVolleyRequest.getRequest("", null)


    }

    override fun iVolleyResponse(response: String) {
        val liveData:LiveData<List<ApprovalModel>>
       val jsonObject = JSONObject(response)

    }


}