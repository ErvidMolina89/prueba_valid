package com.example.pruebavalid.Util

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.pruebavalid.R
import kotlinx.android.synthetic.main.alert_dialogue.*

class DialogueAlert: DialogFragment() {

    companion object {
        var showing = false
    }

    var onDialogueAlertListener: OnDialogueAlertListener? = null
    var tagsDialog: Constants.TagsDialogue? = null
    var titulo: String?                     = null
    var mensaje: String?                    = null
    var accept_btn: String?                 = null
    var cancel_btn: String?                 = null

    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val vista = inflater.inflate(R.layout.alert_dialogue, null, false)
        dialog?.setCanceledOnTouchOutside(false)
        return vista
    }

    override fun onResume() {
        super.onResume()
        dialogueLogic()
        ButtonsListener()
        showing = true
    }

    private fun dialogueLogic(){
        tv_titulo_alert.visibility = View.GONE
        iv_icono_dialogo_alert.visibility = View.GONE

        if(titulo != null){
            //muestra el titulo
            tv_titulo_alert.visibility = View.VISIBLE
            tv_titulo_alert.setText(titulo)
            btnAceptar_alert.setText(accept_btn)
            btnCancelar_alert.setText(cancel_btn)
        }
        if(tagsDialog!!.typeDialogue.rutaIcono != null){
            iv_icono_dialogo_alert.setImageResource(tagsDialog!!.typeDialogue.rutaIcono!!)
            iv_icono_dialogo_alert.visibility = View.VISIBLE
        }

        tv_mensaje_alert.setText(mensaje)

        when(tagsDialog!!.typeDialogue){
            TypeDialogue.INFORMACION -> {
                //oculta el boton
                btnCancelar_alert!!.visibility = View.GONE
            }
            TypeDialogue.ERROR_INTERNET -> {
                btnAceptar_alert!!.visibility = View.VISIBLE
                btnCancelar_alert!!.visibility = View.VISIBLE
            }

            TypeDialogue.ERROR -> {
                btnCancelar_alert!!.visibility = View.GONE
            }
            TypeDialogue.ALERTA -> {
                btnCancelar_alert!!.visibility = View.GONE
            }
            else -> {}
        }
    }

    private fun ButtonsListener(){
        btnAceptar_alert.setOnClickListener(View.OnClickListener {
            dismiss()
            if (onDialogueAlertListener == null){
                return@OnClickListener
            }
            showing = false
            onDialogueAlertListener!!.accept(tagsDialog)
        })
        btnCancelar_alert.setOnClickListener(View.OnClickListener {
            dismiss()
            if (onDialogueAlertListener == null){
                return@OnClickListener
            }
            showing = false
            onDialogueAlertListener!!.cancel(tagsDialog)
        })
    }

    interface OnDialogueAlertListener {
        fun accept(tagsDialog: Constants.TagsDialogue?)
        fun cancel(tagsDialog: Constants.TagsDialogue?)
    }
}