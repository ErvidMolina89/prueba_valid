package com.example.pruebavalid.Base.Implementations

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pruebavalid.R
import com.example.pruebavalid.Util.DialogueGenerico
import com.example.pruebavalid.Util.HiddenKeyBoard
import com.example.pruebavalid.Util.showDialogueGenerico

open class BaseFragment: Fragment() {

    var baseActivity: BaseActivity? = null
    var vista: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        baseActivity = activity as BaseActivity
        HiddenKeyBoard.newInstance(vista!!)
        return vista
    }


    fun dialogueFragment(title: Int,
                                 detail: Int,
                                 type: DialogueGenerico.TypeDialogue){
        DialogueGenerico
            .getInstance()
            .withTitle(title)
            .withText(detail)
            .withTypeDialogue(type)
            .withTextBtnAccept(R.string.btn_aceptar)
            .withActionBtnAccept {
                Log.e("","")
            }
        context?.showDialogueGenerico()
    }

}