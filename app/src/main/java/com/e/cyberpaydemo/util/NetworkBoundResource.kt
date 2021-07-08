package com.e.cyberpaydemo.util

import android.app.DownloadManager
import android.util.Log
import kotlinx.coroutines.flow.*
import java.lang.Exception

inline fun<ResultType, RequestType> networkBoundResource(
    crossinline query: ()->Flow<ResultType>,
    crossinline fetch : suspend ()-> RequestType,
    crossinline saveFetchResult : suspend (RequestType)-> Unit,
    crossinline shouldFetch: (ResultType)->Boolean = {true}
)= flow{
    val data = query().first()

    val flow = if (shouldFetch(data)){
        emit(Resource.Loading(data))

        try {
            saveFetchResult(fetch())
            Log.d("saveT", fetch().toString())
            query().map { Resource.Success(it) }
        } catch (e:Throwable){
            query().map { Resource.Error(e, it) }
        }
    } else{
        query().map { Resource.Success(it) }
    }
    emitAll(flow)

}