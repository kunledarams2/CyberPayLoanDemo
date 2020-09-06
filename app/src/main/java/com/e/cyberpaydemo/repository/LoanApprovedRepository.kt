package com.e.cyberpaydemo.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Response
import com.e.cyberpaydemo.api.MyVolleyRequest
import com.e.cyberpaydemo.api.ResultList
import com.e.cyberpaydemo.model.ApprovalModel
import org.json.JSONArray

class LoanApprovedRepository(private val context: Context) {

    fun fetchLoanApproved(loanStatus:String): LiveData<ResultList<ApprovalModel>> {
        val url = "http://5e8199e5c130270016a372d2.mockapi.io/api/v1/loans"
        val data = MutableLiveData<ResultList<ApprovalModel>>()
        val result = ResultList<ApprovalModel>()

        MyVolleyRequest.getInstance(context).get(url, null,
            Response.Listener { response ->
                log(response)

                    val obj = JSONArray(response)
//                    result.add(ApprovalModel.parse(obj))
                    val dataList = ArrayList<ApprovalModel>()
                    if (obj.length() > 0){
                        for (i in 0 until obj.length()) {
                            val dataObj = obj.getJSONObject(i)
                            if (loanStatus=="true" && dataObj.getBoolean("isLoanApproved")){
                                dataList.add(ApprovalModel.parse(dataObj))
                            } else if (loanStatus=="false" && !dataObj.getBoolean("isLoanApproved")){
                                dataList.add(ApprovalModel.parse(dataObj))
                            }
                        }
                        result.setDataList(dataList)
                        log(result.getDataList()!!.size.toString())
                        data.value = result
                    }

             data.value=result
            },
            Response.ErrorListener {

            })

        return data
    }

    fun log(mgs: String) {
        Log.d(LoanApprovedRepository::class.simpleName, "-_--_--_-_--_-_-$mgs")
    }


}