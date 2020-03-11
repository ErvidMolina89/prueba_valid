package com.example.pruebavalid.DataAccess.RetrofitYesid.Manejador

interface ParametrosServicios {

    enum class Metodos{
        GET,
        POST,
        PUT,
        DELETE;
    }

    fun getURL() : String
    fun getMethodo() : Metodos
}