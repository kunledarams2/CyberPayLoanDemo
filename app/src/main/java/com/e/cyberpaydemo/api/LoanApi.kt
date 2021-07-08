package com.e.cyberpaydemo.api

import com.e.cyberpaydemo.model.LoanModel
import retrofit2.http.GET

interface LoanApi {
    companion object{
        const val  BASE_URL = "http://5e8199e5c130270016a372d2.mockapi.io/api/v1/"
    }

    @GET("loans")
    suspend fun getLoans() : List<LoanModel>
}