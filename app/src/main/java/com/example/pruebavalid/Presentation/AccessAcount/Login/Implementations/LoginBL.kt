package com.example.pruebavalid.Presentation.AccessAcount.Login.Implementations

import android.content.Context
import com.example.pruebavalid.DataAccess.Connection.Resources.Services
import com.example.pruebavalid.DataAccess.Persistence.PreferencesManager
import com.example.pruebavalid.DataAccess.Repositories.IRepository
import com.example.pruebavalid.DataAccess.Repositories.RepoLogin
import com.example.pruebavalid.Models.MessageResponse
import com.example.pruebavalid.Models.User
import com.example.pruebavalid.Presentation.AccessAcount.Login.Interfaces.ILoginBL
import com.example.pruebavalid.Presentation.AccessAcount.Login.Interfaces.ILoginListener

class LoginBL (context: Context, loginListener : ILoginListener) : ILoginBL {

    private val context : Context
    private val listener : ILoginListener

    init {
        this.context = context
        this.listener = loginListener
    }

    override fun startSection(user: User) {
        RepoLogin(context).LogIn(user, ListenerRepositories(), Services.LogIn)
    }

    private inner class ListenerRepositories : IRepository{

        override fun onSuccessResponse(objectResponse: Any?, services: Services) {
            when(services){
                Services.LogIn  -> {
                    listener.responseLogin(objectResponse as User)
                    PreferencesManager.setUser(objectResponse, context)
                }
                else -> {  }
            }
        }

        override fun onFailedResponse(response: MessageResponse, services: Services) {
            when(services){
                Services.LogIn  -> {
                    listener.credentialsIncorrect()
                }
                else -> {  }
            }
        }

    }



}