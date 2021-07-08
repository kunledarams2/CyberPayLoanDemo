package com.e.cyberpaydemo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.e.cyberpaydemo.model.LoanModel


@Database(entities = [LoanModel::class], version = 1 )
abstract class LoanDatabase:RoomDatabase() {

    abstract fun loanDao() :LoansDao
}