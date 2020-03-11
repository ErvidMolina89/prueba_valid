package com.example.pruebavalid.Base.Implementations

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.pruebavalid.R
import com.example.pruebavalid.Util.Constants
import com.example.pruebavalid.Util.DialogueAlert
import com.example.pruebavalid.Util.HiddenKeyBoard

@SuppressLint("Registered")
open class BaseActivity: AppCompatActivity()  {
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HiddenKeyBoard.newInstance(window.decorView)
        App.mContext = this
    }

    fun navigationEntreFragment(rutaContenedor:Int, etiqueta:String? = null, fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(rutaContenedor, fragment).addToBackStack(etiqueta).commit()
    }

    fun showProgress(titleDialog: String?) {
        if (progressDialog != null) {
            closeProgress()
        }
        progressDialog = ProgressDialog(this, R.style.AppCompatAlertProgressStyle)
        progressDialog!!.setMessage(titleDialog)
        progressDialog!!.setCancelable(true)
        progressDialog!!.show()
    }

    fun closeProgress() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
            progressDialog = null
        }
    }

    fun showDialogue(
        title: String?, menssaje: String?,
        btnAccept: String?, btnCancel: String?,
        tagsDialogue: Constants.TagsDialogue,
        onDialogueAlertListener: DialogueAlert.OnDialogueAlertListener
    ){
        try {
            val dialog = DialogueAlert()
            dialog.titulo                     = title
            dialog.mensaje                    = menssaje
            dialog.accept_btn                 = btnAccept
            dialog.cancel_btn                 = btnCancel
            dialog.tagsDialog                 = tagsDialogue
            dialog.onDialogueAlertListener    = onDialogueAlertListener
            dialog.show(supportFragmentManager, tagsDialogue.etiquetas)
        }catch (e: Exception){

        }
    }
}