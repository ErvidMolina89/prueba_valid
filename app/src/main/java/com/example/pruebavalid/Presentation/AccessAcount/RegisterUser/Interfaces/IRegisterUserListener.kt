package com.example.pruebavalid.Presentation.AccessAcount.RegisterUser.Interfaces

import com.example.pruebavalid.Models.User

interface IRegisterUserListener {
    fun responseRegisterUser(user: User)
}