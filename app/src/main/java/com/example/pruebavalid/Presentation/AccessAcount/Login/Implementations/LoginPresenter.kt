package com.example.pruebavalid.Presentation.AccessAcount.Login.Implementations

import android.content.Context
import com.example.pruebavalid.Models.User
import com.example.pruebavalid.Presentation.AccessAcount.Login.Interfaces.ILoginBL
import com.example.pruebavalid.Presentation.AccessAcount.Login.Interfaces.ILoginListener
import com.example.pruebavalid.Presentation.AccessAcount.Login.Interfaces.ILoginPresenter
import com.example.pruebavalid.Presentation.AccessAcount.Login.Interfaces.ILoginView

class LoginPresenter (context: Context,view : ILoginView): ILoginPresenter {

    private val loginView : ILoginView
    private val context : Context
    private val loginBL : ILoginBL
    init {
        this.context = context
        this.loginView = view
        loginBL = LoginBL(context,Listener())
    }

    override fun startSection(user: User) {
        loginBL.startSection(user)
    }



    private inner class Listener : ILoginListener{
        override fun credentialsIncorrect() {
            loginView.credentialsIncorrect()
        }

        override fun responseLogin(user: User) {
            loginView.responseLogin(user)
        }

    }
}