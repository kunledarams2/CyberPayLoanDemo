package com.e.cyberpaydemo.model

import org.json.JSONException
import org.json.JSONObject

class ApprovalModel {

    lateinit var name:String
    lateinit var avatar:String
    lateinit var amount:String
    lateinit var time:String
    lateinit var isLoanApproved:String


    companion object{
        @JvmStatic
        @Throws(JSONException::class)
        fun parse(obj:JSONObject):ApprovalModel{
            val approvalModel= ApprovalModel()
            approvalModel.avatar = obj.getString("avatar")
            approvalModel.amount=obj.getString("amount")
            approvalModel.name = obj.getString("name")
            approvalModel.time= obj.getString("createdAt")
            approvalModel.isLoanApproved=obj.getString("isLoanApproved")

            return approvalModel
        }

    }


}