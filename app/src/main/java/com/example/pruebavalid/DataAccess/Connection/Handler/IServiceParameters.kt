package com.example.pruebavalid.DataAccess.Connection.Handler

interface IServiceParameters {

    enum class Methods{
        GET,
        POST,
        PUT,
        DELETE;
    }

    fun getURL() : String
    fun getMethods() : Methods
}