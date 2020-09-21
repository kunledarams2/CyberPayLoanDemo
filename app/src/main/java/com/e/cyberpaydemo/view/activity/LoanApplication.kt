package com.e.cyberpaydemo.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.cyberspace.cyberpaysdk.CyberpaySdk
import com.cyberspace.cyberpaysdk.TransactionCallback
import com.cyberspace.cyberpaysdk.enums.Mode
import com.cyberspace.cyberpaysdk.model.Transaction
import com.e.cyberpaydemo.R
import kotlinx.android.synthetic.main.fragment_load_application.*
import java.util.*

class LoanApplication : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_load_application)

        CyberpaySdk.initialiseSdk("d5355204f9cf495f853c8f8d26ada19b", Mode.Debug)



        nav_btn.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    DashBoard::class.java
                )
            )
        }

        calculat_loan.setOnClickListener { initiateTransaction() }
    }

    private fun initiateTransaction(){
        val trans= Transaction()
        trans.amount = 10000.0
        trans.customerEmail ="enochdarams05@gmail.com"


        CyberpaySdk.checkoutTransaction(this, trans, object :TransactionCallback(){
            override fun onError(transaction: Transaction, throwable: Throwable) {
                log(throwable.message.toString())
            }

            override fun onSuccess(transaction: Transaction) {
                log(transaction.reference)
            }

            override fun onValidate(transaction: Transaction) {
                log(transaction.description)
            }
        })
    }

    fun log(msg:String){
        Log.d(LoanApplication::class.java.simpleName, "-_-_-_-_-_-_-_--_-__-$msg")
    }
}