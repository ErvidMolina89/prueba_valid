package com.example.pruebavalid.DataAccess.RetrofitYesid.Manejador

import retrofit2.Call
import retrofit2.http.*

/**
 * url dinamica
 * https://stackoverflow.com/questions/32559333/retrofit-2-dynamic-url
 */
interface ServiciosGenericos {

    @GET
    fun getGenerico(@Url url: String) : Call<Any>;

    @POST
    fun postGenerico(@Url url: String) : Call<Any>;

    @PUT
    fun putGenerico(@Url url: String) : Call<Any>;

    @DELETE
    fun deleteGenerico(@Url url: String) : Call<Any>;
}