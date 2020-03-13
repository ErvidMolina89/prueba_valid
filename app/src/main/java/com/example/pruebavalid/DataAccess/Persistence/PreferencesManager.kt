package com.example.pruebavalid.DataAccess.Persistence

import android.content.Context
import android.content.SharedPreferences
import com.example.pruebavalid.Models.BaseModel
import com.example.pruebavalid.Models.User
import com.example.pruebavalid.Util.Constants

class PreferencesManager {

    companion object {

        private fun getUserSharedPreferences(context: Context): SharedPreferences{
            return context.getSharedPreferences(Constants.Persistence.USER_PREFERENCES,Context.MODE_PRIVATE)
        }

        fun setUser (user: User, context: Context){
            val editor: SharedPreferences.Editor = getUserSharedPreferences(context).edit()
            editor.putString(Constants.Keys.USER, user.toJsonString())
            editor.apply()
        }
        fun getUser (context: Context):User? {
            val userSharedPreferences: SharedPreferences = getUserSharedPreferences(context)
            val jsonString: String = userSharedPreferences.getString(Constants.Keys.USER, Constants.Persistence.USER_NOT_FOUND)
            if (jsonString.equals(Constants.Persistence.USER_NOT_FOUND)){
                return null
            }
            return BaseModel.objectFromJson(jsonString, User::class.java) as User
        }
        fun clean(context: Context){
            val editor: SharedPreferences.Editor = getUserSharedPreferences(context).edit()
            editor.clear()
            editor.apply()
        }

    }
}