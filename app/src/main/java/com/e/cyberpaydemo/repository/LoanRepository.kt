package com.e.cyberpaydemo.repository

import android.util.Log
import androidx.room.withTransaction
import com.e.cyberpaydemo.api.LoanApi
import com.e.cyberpaydemo.data.LoanDatabase
import com.e.cyberpaydemo.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class LoanRepository @Inject constructor(
    private val api: LoanApi,
    private val db: LoanDatabase
) {

    private val loadDao = db.loanDao()

    fun getAllLoans() = networkBoundResource(
        query = {
        loadDao.getAllLoans()
    }, fetch = {
        delay(2000)
        api.getLoans()
    }, saveFetchResult = { loans ->
        db.withTransaction {
            Log.d("RepoData", loans.toString())
            loadDao.deleteAllLoans()
            loadDao.insertLoan(loans)
        }
    })

}