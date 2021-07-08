package com.e.cyberpaydemo.viewmodel


import androidx.lifecycle.*
import com.e.cyberpaydemo.repository.LoanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoanApprovalViewModel @Inject constructor(repository: LoanRepository):
    ViewModel() {

    val loans = repository.getAllLoans().asLiveData()

}