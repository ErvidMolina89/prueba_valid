package com.example.pruebavalid.DataAccess.Firebase

import android.content.Context
import android.util.Log
import com.example.pruebavalid.Models.User
import com.google.firebase.auth.FirebaseAuth

class Login (context: Context, messageSuccess : ()-> Unit, failure : ()->Unit) {

    private val TAG = "Login"
    private val context : Context
    private val messageSuccess : (()->Unit)
    private val failure : (()->Unit)

    init {
        this.context = context
        this.messageSuccess = messageSuccess
        this.failure = failure
    }

    fun loginWithUser(user : User){
        val instanceFirebase = FirebaseAuth.getInstance()
        instanceFirebase
            .signInWithEmailAndPassword(user.email!!,user.password!!)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    messageSuccess.invoke()
                    return@addOnCompleteListener
                }
                Log.e(TAG,"",it.exception)
                failure.invoke()
            }
    }

    fun registerUser(user: User){
        val instanceFirebase = FirebaseAuth.getInstance()

        instanceFirebase
            .createUserWithEmailAndPassword(user.email!!,user.password!!)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    messageSuccess.invoke()
                    return@addOnCompleteListener
                }
                Log.e(TAG,"",it.exception)
                failure.invoke()
            }
    }


}