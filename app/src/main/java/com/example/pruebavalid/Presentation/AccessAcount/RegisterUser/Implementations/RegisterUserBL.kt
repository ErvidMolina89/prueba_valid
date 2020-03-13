package com.example.pruebavalid.Presentation.AccessAcount.RegisterUser.Implementations

import android.content.Context
import com.example.pruebavalid.DataAccess.Connection.Resources.Services
import com.example.pruebavalid.DataAccess.Repositories.IRepository
import com.example.pruebavalid.DataAccess.Repositories.RepoLogin
import com.example.pruebavalid.Models.MessageResponse
import com.example.pruebavalid.Models.User
import com.example.pruebavalid.Presentation.AccessAcount.RegisterUser.Interfaces.IRegisterUserBL
import com.example.pruebavalid.Presentation.AccessAcount.RegisterUser.Interfaces.IRegisterUserListener

class RegisterUserBL (context: Context, registerUserListener : IRegisterUserListener) :
    IRegisterUserBL {

    private val context : Context
    private val listener : IRegisterUserListener

    init {
        this.context = context
        this.listener = registerUserListener
    }

    override fun registerUser(user: User) {
        RepoLogin(context).RegisterUser(user, ListenerRepositories(), Services.registerUser)
    }

    private inner class ListenerRepositories : IRepository{

        override fun onSuccessResponse(objectResponse: Any?, services: Services) {
            when(services){
                Services.registerUser  -> {
                    listener.responseRegisterUser(objectResponse as User)
                }
                else -> {  }
            }
        }

        override fun onFailedResponse(response: MessageResponse, services: Services) {
            when(services){
                Services.registerUser  -> {  }
                else -> {  }
            }
        }

    }



}