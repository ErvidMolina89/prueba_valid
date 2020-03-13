package com.example.pruebavalid.Presentation.AccessAcount.Login.Implementations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pruebavalid.Base.Implementations.App
import com.example.pruebavalid.Base.Implementations.BaseFragment
import com.example.pruebavalid.Models.User
import com.example.pruebavalid.Presentation.AccessAcount.Login.Interfaces.ILoginPresenter
import com.example.pruebavalid.Presentation.AccessAcount.Login.Interfaces.ILoginView
import com.example.pruebavalid.R
import com.example.pruebavalid.Util.DialogueGenerico
import com.example.pruebavalid.Util.isNetworkAvailable
import kotlinx.android.synthetic.main.fragment_login.*

class LoginView: BaseFragment() {

    private var viewPrincial : View ?= null
    private var presenter: ILoginPresenter? = null
    private var actionPresenter = actionViewPresenter()
    var listenerFragmentLogin: OnFragmentInteractionListener? = null
    private var user : User? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewPrincial = inflater.inflate(R.layout.fragment_login,null,false)
        presenter = LoginPresenter(App.mContext!!, actionPresenter)
        return viewPrincial
    }

    override fun onResume() {
        super.onResume()
        btn_start_section.setOnClickListener{

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
        tv_register_user.setOnClickListener {
            listenerFragmentLogin?.callViewRegisterUser()
        }
    }

    private fun validateFields(): Boolean{
        if (!et_email_login.text.isNullOrEmpty()
            && !et_Pass_Login.text.isNullOrEmpty()){
            return true
        }
        return false
    }

    private fun captureInformationFields(){
        user = User()
        user?.email         = et_email_login.text.toString()
        user?.password      = et_Pass_Login.text.toString()
        startSection(user!!)
    }

    private fun startSection(user: User){
        presenter?.startSection(user)
    }

    inner class actionViewPresenter: ILoginView{
        override fun credentialsIncorrect() {
            dialogueFragment(R.string.credentials_incorrect,
                R.string.details_credentials_incorrect,
                DialogueGenerico.TypeDialogue.ADVERTENCIA)
        }

        override fun responseLogin(user: User) {
            listenerFragmentLogin?.callViewSelectService()
        }

    }

    interface OnFragmentInteractionListener {
        fun callViewRegisterUser()
        fun callViewSelectService()
    }



    companion object {
        fun newInstance() =
            LoginView().apply {
                arguments = Bundle().apply {}
            }
    }
}