package com.e.cyberpaydemo.api

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.util.LruCache
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.e.cyberpaydemo.callback.IVolleyResponse
import java.util.*
import kotlin.collections.HashMap

class MyVolleyRequest {

    private var mRequestQueue: RequestQueue? = null
    private var context: Context? = null
    private var iVolleyRequest: IVolleyResponse? = null
    var imageLoader: ImageLoader? = null


    val requestQueue: RequestQueue
        get() {
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(context!!.applicationContext)
            }
            return mRequestQueue!!
        }

    private constructor(cxt: Context, miVolleyResponse: IVolleyResponse) {
        this.context = cxt
        this.iVolleyRequest = miVolleyResponse
        mRequestQueue = requestQueue
        imageLoader = ImageLoader(mRequestQueue, object : ImageLoader.ImageCache {
            private val mCache = LruCache<String, Bitmap>(10)
            override fun getBitmap(url: String?): Bitmap {
                return mCache.get(url)
            }

            override fun putBitmap(url: String?, bitmap: Bitmap?) {
                mCache.put(url, bitmap)
            }
        })
    }

    private constructor(cxt: Context) {
        this.context = cxt
        mRequestQueue = requestQueue
        imageLoader = ImageLoader(mRequestQueue, object : ImageLoader.ImageCache {
            private val mCache = LruCache<String, Bitmap>(10)
            override fun getBitmap(url: String?): Bitmap {
                return mCache.get(url)
            }

            override fun putBitmap(url: String?, bitmap: Bitmap?) {
                mCache.put(url, bitmap)
            }
        })
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }

    // getMethod
    fun getRequest(url: String, params: HashMap<String, String>?) {
        val getRequest = object : StringRequest(Request.Method.GET, url, { response ->
            iVolleyRequest!!.iVolleyResponse(response)
        }, { error ->
            iVolleyRequest!!.iVolleyResponse(error.message.toString())
        }) {
            override fun getParams(): MutableMap<String, String> {
                return params!!
            }
        }

        addToRequestQueue(getRequest)
    }

    // postMethod
    fun postRequest(url: String, params: HashMap<String, String>) {
        val getRequest = object :StringRequest(Method.POST, url, { response ->
            iVolleyRequest!!.iVolleyResponse(response)
        }, { error ->
            iVolleyRequest!!.iVolleyResponse(error.message.toString())
        }){
            override fun getParams(): MutableMap<String, String> {
                return params
            }
        }

        addToRequestQueue(getRequest)
    }


    fun get(url: String?, params: Map<String, String>?, listener: Response.Listener<String>?, errorListener: Response.ErrorListener?
    ) {
        val request: StringRequest = object : StringRequest(Method.GET, url,  listener, errorListener
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val map: MutableMap<String, String> = HashMap<String, String>()
                map["Accept"] = "application/json"
                /*  if (API.isLoggedIn(mApi.getContext())) {
                      val credentials: Map<String, String> =
                          API.getCredentials(mApi.getContext())
                      val token = credentials[API.SESSION_ID]
                      map["sessionid"] = token
                      //map.put("Authorization", token);
                      Log.d("SESSION_ID", token)
                  }*/
                map["Content-Type"] = "application/x-www-form-urlencoded"
                return map
            }

            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                return params!!
            }
        }
        addToRequestQueue(request)
//        val tag: String = mApi.getTag(url, "post")
//        request.tag = tag
//        mApi.getRequestQueue().add(request)
    }


    companion object{
        private var mInstance:MyVolleyRequest?=null

        @Synchronized
        fun getInstance(context: Context):MyVolleyRequest{
            if (mInstance==null){
                mInstance= MyVolleyRequest(context)
            }
            return mInstance!!

        }

        @Synchronized
        fun getInstance(context: Context,iVolleyResponse: IVolleyResponse):MyVolleyRequest{
            if (mInstance==null){
                mInstance= MyVolleyRequest(context,iVolleyResponse)
            }
            return mInstance!!

        }
    }


}