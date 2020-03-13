package com.example.pruebavalid.Presentation.AccessAcount.Login.Interfaces

import com.example.pruebavalid.Models.User

interface ILoginListener {
    fun responseLogin(user: User)
    fun credentialsIncorrect()
}