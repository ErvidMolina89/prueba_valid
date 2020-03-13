package com.example.pruebavalid.Base.Implementations

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.pruebavalid.R
import com.example.pruebavalid.Util.DialogueGenerico
import com.example.pruebavalid.Util.HiddenKeyBoard

@SuppressLint("Registered")
open class BaseActivity: AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HiddenKeyBoard.newInstance(window.decorView)
        App.mContext = this
    }

    fun navigationEntreFragment(rutaContenedor:Int, etiqueta:String? = null, fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(rutaContenedor, fragment).addToBackStack(etiqueta).commit()
    }

    override fun onPause() {
        super.onPause()
        try {
            DialogueGenerico.getInstance().dismiss()
        } catch (e: Exception) {
        }
    }
}