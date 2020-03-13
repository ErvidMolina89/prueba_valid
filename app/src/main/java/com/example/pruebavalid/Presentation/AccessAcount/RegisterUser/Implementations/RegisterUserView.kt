package com.example.pruebavalid.Presentation.AccessAcount.RegisterUser.Implementations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pruebavalid.Base.Implementations.App
import com.example.pruebavalid.Base.Implementations.BaseFragment
import com.example.pruebavalid.Models.User
import com.example.pruebavalid.Presentation.AccessAcount.RegisterUser.Interfaces.IRegisterUserPresenter
import com.example.pruebavalid.Presentation.AccessAcount.RegisterUser.Interfaces.IRegisterUserView
import com.example.pruebavalid.R
import com.example.pruebavalid.Util.DialogueGenerico
import com.example.pruebavalid.Util.isNetworkAvailable
import com.example.pruebavalid.Util.showDialogueGenerico
import kotlinx.android.synthetic.main.fragment_create_user.*

class RegisterUserView: BaseFragment() {

    private var viewPrincipal : View ?= null
    private var presenter: IRegisterUserPresenter? = null
    private var actionPresenter = actionViewPresenter()
    var listenerFragmentRegisterUser: OnFragmentInteractionListener? = null
    private var user : User? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewPrincipal = inflater.inflate(R.layout.fragment_create_user,null,false)
        presenter = RegisterUserPresenter(App.mContext!!, actionPresenter)
        return viewPrincipal
    }

    override fun onResume() {
        super.onResume()
        btnCrear_register.setOnClickListener {
            if (!isNetworkAvailable(App.mContext!!)) {
                dialogueFragment(R.string.internet,
                    R.string.not_connection_internet,
                    DialogueGenerico.TypeDialogue.ADVERTENCIA)
                return@setOnClickListener
            }
            if (validateFields()){
                captureInformationFields()
            } else {
                dialogueFragment(R.string.fields_empty,
                    R.string.details_fields_empty,
                    DialogueGenerico.TypeDialogue.ADVERTENCIA)
            }
        }
    }

    private fun validateFields(): Boolean{
        if (!et_email_register.text.isNullOrEmpty()
            && !et_pass_register.text.isNullOrEmpty()
            && !et_conf_pass_register.text.isNullOrEmpty()){
            return true
        }
        return false
    }

    private fun captureInformationFields(){
        if (validatePassword()) {
            user = User()
            user?.email = et_email_register.text.toString()
            user?.password = et_pass_register.text.toString()
//            user?.conf_password = et_conf_pass_register.text.toString()
            registerUser(user!!)
        }else {
            dialogueFragment(R.string.password,
                R.string.details_password,
                DialogueGenerico.TypeDialogue.ADVERTENCIA)
        }
    }

    private fun validatePassword(): Boolean{
        if (et_pass_register.text.toString() == et_conf_pass_register.text.toString()){
            return true
        }
        return false
    }

    private fun registerUser(user: User){
        presenter?.registerUser(user)
    }

    inner class actionViewPresenter: IRegisterUserView {
        override fun responseRegisterUser(user: User) {
            listenerFragmentRegisterUser?.navigationLogin()
        }

    }

    interface OnFragmentInteractionListener {
        fun navigationLogin()

    }

    companion object {
        fun newInstance() =
            RegisterUserView().apply {
                arguments = Bundle().apply {}
            }
    }
}