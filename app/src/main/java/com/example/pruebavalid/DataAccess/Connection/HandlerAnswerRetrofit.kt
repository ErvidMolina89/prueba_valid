package com.example.pruebavalid.DataAccess.Connection

import android.util.Log
import co.com.mitiempo.retrofitgenerico.RetrofitManager.IRetrofitParcelable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by exsis on 7/11/17.
 */
class HandlerAnswerRetrofit : Callback<Any> {

    val T = "ErrorRespRetro"
    private var listenerOfFailure : ((Int,Int)->Unit)?= null
    fun withListenerOfFailure(listenerOfFailure : ((Int,Int)->Unit)?) : HandlerAnswerRetrofit{
        this.listenerOfFailure = listenerOfFailure
        return this
    }

    private var listenerAnswerObjetc : ((IRetrofitParcelable?)->Unit)?= null
    fun withListenerAnswerObjetcs(listenerAnswerObjetc : ((IRetrofitParcelable?)->Unit)?) : HandlerAnswerRetrofit{
        this.listenerAnswerObjetc =  listenerAnswerObjetc
        return this
    }

    private var listenerListObjetc : ((MutableList<IRetrofitParcelable>?)->Unit)?= null
    fun withListenerListObjetcs(listenerListObjetc : ((MutableList<IRetrofitParcelable>?)->Unit)?) : HandlerAnswerRetrofit{
        this.listenerListObjetc = listenerListObjetc
        return this
    }

    private var myClass : Class<*> ?= null
    fun withExpectedObject(myClass : Class<*>? ) : HandlerAnswerRetrofit{
        this.myClass = myClass
        return this
    }

    override fun onFailure(call: Call<Any>, t: Throwable) {
        Log.e("","")
    }

    override fun onResponse(call: Call<Any>, response: Response<Any>) {
        answerPositive(response)
    }

    private fun answerPositive(response: Response<Any>) {
        val json = Gson().toJson(response.body())
        if(generateSingleObject(json)){
            return
        }
        if(generateListObject(json)){
            return
        }

        listenerAnswerObjetc?.invoke(null)
        listenerListObjetc?.invoke(null)
    }

    private fun generateSingleObject(json : String) : Boolean{
        return try {
            val myObject = Gson().fromJson(json, myClass!!)
            listenerAnswerObjetc?.invoke(myObject as? IRetrofitParcelable)
            true
        }catch (e : Exception){
            false
        }
    }

    private fun generateListObject(json : String) : Boolean{
        return try {
            val myType = object : TypeToken<MutableList<Any>>(){}.type
            val myList = Gson().fromJson<Any>(json,myType) as MutableList<Any>
            val castedList = emptyArray<Any>().toMutableList()
            for (item in myList){
                val json = Gson().toJson(item)
                val myObject = Gson().fromJson(json,myClass!!)
                castedList.add(myObject)
            }
            listenerListObjetc?.invoke(castedList as? MutableList<IRetrofitParcelable>)
            true
        }catch (e  : Exception){
            false
        }
    }

}