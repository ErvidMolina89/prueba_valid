package com.example.pruebavalid.Util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.Guideline
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.pruebavalid.R

class DialogueGenerico private constructor(): DialogFragment() {


    companion object{
        private var instance : DialogueGenerico?= null

        fun getInstance() : DialogueGenerico {
            if(instance == null )
            {
                instance = DialogueGenerico()
            }
            return instance!!
        }
    }



    private var containerPrincipal : View ?= null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        containerPrincipal = inflater.inflate(R.layout.dialogo_generico,null,false)
        isCancelable = false

        FindElementsView()
        loadViewInformation()
        return containerPrincipal
    }

    private var image_dialog : ImageView ?= null
    private var details_message : TextView ?= null
    private var title : TextView ?= null
    private var btn_Accept : Button ?= null
    private var btn_Cancel : Button?= null
    private var guideLine : Guideline?= null


    private fun FindElementsView(){

        image_dialog    = containerPrincipal?.findViewById(R.id.image_dialog )
        details_message = containerPrincipal?.findViewById(R.id.detail_message )
        title           = containerPrincipal?.findViewById(R.id.title_dialog )
        btn_Accept      = containerPrincipal?.findViewById(R.id.btn_accept_dialog )
        btn_Cancel      = containerPrincipal?.findViewById(R.id.btn_cancel_dialog )
        guideLine       = containerPrincipal?.findViewById(R.id.line_dialog )

        addListeners()
    }

    private fun loadViewInformation(){
        selectIcon()
        introduceTitle()
        introduceMessage()
        introduceBtnAccept()
        introduceBtnCancel()
    }

    private fun selectIcon(){
        image_dialog?.setImageResource(typeDialog.getIcono())
    }

    private fun introduceTitle(){
        if(routeTitle == null ){
            title?.visibility = View.GONE
            return
        }
        title?.visibility = View.VISIBLE
        title?.setText(routeTitle!!)
    }

    private fun introduceMessage(){
        if(routeText == null ){
            details_message?.visibility = View.GONE
            return
        }
        details_message?.visibility = View.VISIBLE
        details_message?.setText(routeText!!)
    }

    private fun introduceBtnAccept(){
        if(routeTextBtnAccept == null ){
            btn_Accept?.setText(R.string.btn_aceptar)
            return
        }
        btn_Accept?.setText(routeTextBtnAccept!!)
    }

    private fun introduceBtnCancel(){
        if(routeTextBtnCancel == null ){
            btn_Cancel?.visibility = View.GONE
            guideLine?.setGuidelinePercent(1.toFloat())
            return
        }
        guideLine?.setGuidelinePercent((0.5).toFloat())
        btn_Cancel?.visibility = View.VISIBLE
        btn_Cancel?.setText(routeTextBtnCancel!!)
    }

    private fun addListeners(){
        btn_Accept?.setOnClickListener {
            dismiss()
            invokeActionBtnAccept?.invoke()
            clearViewElements()
        }
        btn_Cancel?.setOnClickListener {
            dismiss()
            invokeActionBtnCancel?.invoke()
            clearViewElements()
        }
    }

    private fun clearViewElements(){

        routeTextBtnCancel = null
        invokeActionBtnAccept = null
        invokeActionBtnCancel =  null
    }


    override fun dismiss() {
        if(fragmentManager == null ){
           return
        }
        super.dismiss()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if(isAdded){
            return
        }
        super.show(manager, tag)
    }



    enum class TypeDialogue{
        ERROR,
        ADVERTENCIA,
        OK;

        fun getIcono(): Int{
            return when(this){
                OK -> R.drawable.ic_check
                ADVERTENCIA -> R.drawable.ic_warning
                ERROR -> R.drawable.ic_close
            }
        }
    }

    private var invokeActionBtnAccept:(()->Unit)?= null
    fun withActionBtnAccept(actionAccept : ()->Unit) : DialogueGenerico {
        this.invokeActionBtnAccept = actionAccept
        return this
    }

    private var invokeActionBtnCancel:(()->Unit)?= null
    fun withActionBtnCancel(ActionCancel : ()->Unit) : DialogueGenerico {
        this.invokeActionBtnCancel = ActionCancel
        return this
    }

    private @StringRes
    var routeTitle : Int ?= null
    fun withTitle(@StringRes routeString : Int): DialogueGenerico {
        this.routeTitle = routeString
        return this
    }

    private @StringRes
    var routeText : Int ?= null
    fun withText(@StringRes routeString : Int): DialogueGenerico {
        this.routeText = routeString
        return this
    }

    private @StringRes
    var routeTextBtnAccept : Int ?= null
    fun withTextBtnAccept(@StringRes routeString : Int): DialogueGenerico {
        this.routeTextBtnAccept = routeString
        return this
    }

    private @StringRes
    var routeTextBtnCancel : Int ?= null
    fun withTextBtnCancel(@StringRes routeString : Int): DialogueGenerico {
        this.routeTextBtnCancel = routeString
        return this
    }

    private var typeDialog : TypeDialogue = TypeDialogue.OK
    fun withTypeDialogue(typeDialogue : TypeDialogue = TypeDialogue.OK) : DialogueGenerico {
        this.typeDialog = typeDialogue
        return this
    }

    fun showDialogue(fragmentManager: FragmentManager, tag : String){
        show(fragmentManager, tag)
    }

}
