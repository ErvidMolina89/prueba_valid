package com.example.pruebavalid.DataAccess.RetrofitYesid.Manejador

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManejadorRespuestasRetrofit  : Callback<Any> {

    val T = "ErrorRespRetro"
    private var escuchadorDeFallas : ((Int,Int)->Unit)?= null
    fun conEscuchadorFallas(escuchadorDeFallas : ((Int,Int)->Unit)?) : ManejadorRespuestasRetrofit{
        this.escuchadorDeFallas = escuchadorDeFallas
        return this
    }

    private var escuchadorRespuestaObjeto : ((RetrofitParcelable?)->Unit)?= null
    fun conEscuchadorRespuestaObjeto(escuchadorRespuestaObjeto : ((RetrofitParcelable?)->Unit)?) : ManejadorRespuestasRetrofit{
        this.escuchadorRespuestaObjeto =  escuchadorRespuestaObjeto
        return this
    }

    private var escuchadorListaObjetos : ((MutableList<RetrofitParcelable>?)->Unit)?= null
    fun conEscuchadorListaObjetos(escuchadorListaObjetos : ((MutableList<RetrofitParcelable>?)->Unit)?) : ManejadorRespuestasRetrofit{
        this.escuchadorListaObjetos = escuchadorListaObjetos
        return this
    }

    private var clase : Class<*> ?= null
    fun conObjetoEsperado(clase : Class<*>? ) : ManejadorRespuestasRetrofit{
        this.clase = clase
        return this
    }

    override fun onFailure(call: Call<Any>, t: Throwable) {
        Log.e("","")
    }

    override fun onResponse(call: Call<Any>, response: Response<Any>) {
        respuestaPositiva(response)
    }

    private fun respuestaPositiva(response: Response<Any>) {
        val json = Gson().toJson(response.body())
        if(genereUnicoObjeto(json)){
            return
        }
        if(generoUnaListaObjetos(json)){
            return
        }

        escuchadorRespuestaObjeto?.invoke(null)
        escuchadorListaObjetos?.invoke(null)
    }

    private fun genereUnicoObjeto(json : String) : Boolean{
        return try {
            val objeto = Gson().fromJson(json, clase!!)
            escuchadorRespuestaObjeto?.invoke(objeto as? RetrofitParcelable)
            true
        }catch (e : Exception){
            false
        }
    }

    private fun generoUnaListaObjetos(json : String) : Boolean{
        return try {
            val tipo = object : TypeToken<MutableList<Any>>(){}.type
            val lista = Gson().fromJson<Any>(json,tipo) as MutableList<Any>
            val listaCasteada = emptyArray<Any>().toMutableList()
            for (item in lista){
                val json = Gson().toJson(item)
                val objeto = Gson().fromJson(json,clase!!)
                listaCasteada.add(objeto)
            }
            escuchadorListaObjetos?.invoke(listaCasteada as? MutableList<RetrofitParcelable>)
            true
        }catch (e  : Exception){
            false
        }
    }
}