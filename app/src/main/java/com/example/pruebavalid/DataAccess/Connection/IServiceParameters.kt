package com.example.pruebavalid.DataAccess.Connection

interface IServiceParameters {
    enum class Methods{
        GET,
        POST,
        PUT,
        DELETE;
    }

    fun getURL() : String
    fun getMethod() : Methods
}