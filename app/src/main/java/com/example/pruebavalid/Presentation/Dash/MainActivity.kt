package com.example.pruebavalid.Presentation.Dash

import android.os.Bundle
import android.util.Log
import com.example.pruebavalid.DataAccess.Connection.Resources.Services
import com.example.pruebavalid.Base.Implementations.App
import com.example.pruebavalid.Base.Implementations.BaseActivity
import com.example.pruebavalid.DataAccess.Connection.Handler.RetrofitProxy
import com.example.pruebavalid.DataAccess.Connection.Handler.IRetrofitParcelable
import com.example.pruebavalid.Models.ListTopArtists
import com.example.pruebavalid.Models.ListTrack
import com.example.pruebavalid.Presentation.AccessAcount.Login.Implementations.LoginView
import com.example.pruebavalid.Presentation.AccessAcount.RegisterUser.Implementations.RegisterUserView
import com.example.pruebavalid.Presentation.Dash.FragmentListSongs.Implementations.FragmentListSongs
import com.example.pruebavalid.Presentation.Dash.Home.Implementations.SelectServiceView
import com.example.pruebavalid.R

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        invokeFragmentInit()
    }

    override fun onBackPressed() {
        if (LoginView.newInstance().isVisible){
            return
        }
        super.onBackPressed()
    }

    private fun invokeFragmentInit(){
        val fragment = LoginView()
        fragment.listenerFragmentLogin = onActionViewLogin()
        navigationEntreFragment(R.id.container_fragment_dash, null,  fragment)
    }

    inner class onActionViewLogin: LoginView.OnFragmentInteractionListener{
        override fun callViewSelectService() {
            navigationViewSelectService()
        }

        override fun callViewRegisterUser() {
            navigationViewRegisterUser()
        }

    }

    private fun navigationViewRegisterUser(){
        val fragment = RegisterUserView()
        fragment.listenerFragmentRegisterUser = onActionViewRegisterUser()
        navigationEntreFragment(R.id.container_fragment_dash, null, fragment)
    }

    private fun navigationViewSelectService(){
        val fragment = SelectServiceView()
        fragment.listenerFragmentSelectService = onActionViewSelectService()
        navigationEntreFragment(R.id.container_fragment_dash, null, fragment)
    }

    private fun navigationListSong(list: ListTrack?, listArtists: ListTopArtists?, int: Int){
        val fragment = FragmentListSongs()
        fragment.typeService = int
        if (list == null){
            fragment.listTopArtists = listArtists
        } else{
            fragment.listTracks = list
        }
        navigationEntreFragment(R.id.container_fragment_dash, null, fragment)
    }

    inner class onActionViewRegisterUser: RegisterUserView.OnFragmentInteractionListener{
        override fun navigationLogin() {
            invokeFragmentInit()
        }

    }

    inner class onActionViewSelectService: SelectServiceView.OnFragmentInteractionListener{
        override fun callViewListServiceTrack(listTracks: ListTrack) {
            navigationListSong(listTracks, null, 1)
        }
        override fun callViewListServiceTopArtists(listTopArtists: ListTopArtists) {
            navigationListSong(null, listTopArtists, 2)
        }

    }

    inner class onActionListSong: FragmentListSongs.OnFragmentInteractionListener {
        override fun navigationDetalleSongs() {

        }

    }
}
