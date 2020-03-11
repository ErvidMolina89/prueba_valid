package com.example.pruebavalid.Base.Implementations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pruebavalid.Util.HiddenKeyBoard

open class BaseFragment: Fragment() {

    var baseActivity: BaseActivity? = null
    var vista: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        baseActivity = activity as BaseActivity
        HiddenKeyBoard.newInstance(vista!!)
        return vista
    }
}