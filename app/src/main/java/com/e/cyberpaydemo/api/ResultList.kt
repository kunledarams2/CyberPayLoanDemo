package com.e.cyberpaydemo.api


 open class ResultList<T> {


    private var successful = false
    private var dataList: List<T>? = null
    private var data: T? = null
    private var message: String? = null

    private fun setSuccessful(successful: Boolean) {
        this.successful = successful
    }

    fun setData(data: T) {
        this.data = data
        setSuccessful(true)
    }

    fun setDataList(list: List<T>?) {
        dataList = list
        setSuccessful(true)
    }

    fun getDataList(): List<T>? {
        return dataList
    }

    fun getData(): T? {
        return data
    }

    fun isSuccessful(): Boolean {
        return successful
    }

    fun setMessage(message: String?) {
        this.message = message
        successful = false
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(msg: String?, status: Boolean) {
        setMessage(msg)
        setSuccessful(status)
    }


}