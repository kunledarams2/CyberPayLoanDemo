package com.e.cyberpaydemo.view.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.e.cyberpaydemo.R
import com.e.cyberpaydemo.api.MyVolleyRequest
import com.e.cyberpaydemo.callback.IVolleyResponse
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var getResponse = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        load_request_wrapper.setOnClickListener {
            Toast.makeText(this, "okay", Toast.LENGTH_LONG).show()
            fetchData()
        }


    }

   private fun fetchData() {
         MyVolleyRequest.getInstance(this@MainActivity, object :IVolleyResponse{
            override fun iVolleyResponse(response: String) {
                Toast.makeText(this@MainActivity, response, Toast.LENGTH_LONG).show()
            }
        }).getRequest(
//                "http://5e8199e5c130270016a372d2.mockapi.io/api/v1/loans",
            "http://161.35.87.69:9000/tremendoc/api/countries",
            null
        )



    }

//    override fun iVolleyResponse(response: String) {
//        getResponse = response
//
//        Log.d(MainActivity::class.java.simpleName, getResponse)
//
//    }


}