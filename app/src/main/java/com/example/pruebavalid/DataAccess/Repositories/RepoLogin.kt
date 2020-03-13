package com.example.pruebavalid.DataAccess.Repositories

import android.content.Context
import com.example.pruebavalid.DataAccess.Firebase.Login
import com.example.pruebavalid.Models.MessageResponse
import com.example.pruebavalid.Models.User
import com.example.pruebavalid.DataAccess.Connection.Resources.Services

class RepoLogin (context: Context) {

    private val context : Context
    init {
        this.context = context
    }

    fun < T: IRepository>LogIn(user : User, responder : T, servicio : Services){
        Login(context, {
            responder.onSuccessResponse(user, servicio)
        },{
            responder.onFailedResponse(MessageResponse(), servicio)
        }).loginWithUser(user)
    }

    fun < T: IRepository>RegisterUser(user : User, responder : T, servicio : Services){
        Login(context, {
            responder.onSuccessResponse(user, servicio)
        },{
            responder.onFailedResponse(MessageResponse(), servicio)
        }).registerUser(user)
    }
}