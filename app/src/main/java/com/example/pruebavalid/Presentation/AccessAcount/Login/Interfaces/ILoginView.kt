package com.example.pruebavalid.Presentation.AccessAcount.Login.Interfaces

import com.example.pruebavalid.Models.User

interface ILoginView {
    fun responseLogin(user: User)
    fun credentialsIncorrect()
}