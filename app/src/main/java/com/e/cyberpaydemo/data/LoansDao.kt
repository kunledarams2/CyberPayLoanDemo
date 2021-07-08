package com.e.cyberpaydemo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.e.cyberpaydemo.model.LoanModel
import kotlinx.coroutines.flow.Flow

@Dao
interface LoansDao {

    @Query("SELECT * FROM loans")
    fun getAllLoans():Flow<List<LoanModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLoan(loans:List<LoanModel>)

    @Query("DELETE  FROM loans")
    suspend fun deleteAllLoans()


}