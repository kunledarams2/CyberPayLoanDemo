package com.e.cyberpaydemo.api

import android.content.Context
import android.graphics.Bitmap
import android.util.LruCache
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.e.cyberpaydemo.callback.IVolleyResponse

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
    fun getRequest(url: String, params: HashMap<String, String>) {
        val getRequest = object : StringRequest(Request.Method.GET, url, { response ->
            iVolleyRequest!!.iVolleyResponse(response)
        }, { error ->
            iVolleyRequest!!.iVolleyResponse(error.message.toString())
        }) {
            override fun getParams(): MutableMap<String, String> {
                return params
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