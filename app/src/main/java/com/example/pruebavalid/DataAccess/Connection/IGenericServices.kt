package com.example.pruebavalid.DataAccess.Connection

import retrofit2.Call
import retrofit2.http.*

/**
 * Created by exsis on 7/11/17.
 */
interface IGenericServices {

    @GET
    fun getGeneric(@Url url: String) : Call<Any>;

    @POST
    fun postGeneric(@Url url: String) : Call<Any>;

    @PUT
    fun putGeneric(@Url url: String) : Call<Any>;

    @DELETE
    fun deleteGeneric(@Url url: String) : Call<Any>;

}