package com.example.pruebavalid.Presentation.AccessAcount.RegisterUser.Implementations

import android.content.Context
import com.example.pruebavalid.Models.User
import com.example.pruebavalid.Presentation.AccessAcount.RegisterUser.Interfaces.IRegisterUserBL
import com.example.pruebavalid.Presentation.AccessAcount.RegisterUser.Interfaces.IRegisterUserListener
import com.example.pruebavalid.Presentation.AccessAcount.RegisterUser.Interfaces.IRegisterUserPresenter
import com.example.pruebavalid.Presentation.AccessAcount.RegisterUser.Interfaces.IRegisterUserView

class RegisterUserPresenter (context: Context, view : IRegisterUserView): IRegisterUserPresenter {

    private val registerUserView : IRegisterUserView
    private val context : Context
    private val registerUserBL : IRegisterUserBL
    init {
        this.context = context
        this.registerUserView = view
        registerUserBL = RegisterUserBL(context,Listener())
    }

    override fun registerUser(user: User) {
        registerUserBL.registerUser(user)
    }



    private inner class Listener : IRegisterUserListener {
        override fun responseRegisterUser(user: User) {
            registerUserView.responseRegisterUser(user)
        }

    }
}