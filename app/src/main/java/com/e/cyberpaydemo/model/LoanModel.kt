package com.e.cyberpaydemo.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "loans")
data class LoanModel (
    @PrimaryKey val name:String,
    val avatar :String,
    val amount :String,
    val createdAt:String,
    val isLoanApproved:Boolean
)
