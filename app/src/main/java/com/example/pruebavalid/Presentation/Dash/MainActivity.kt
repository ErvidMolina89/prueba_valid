package com.example.pruebavalid.Presentation.Dash

import android.os.Bundle
import android.util.Log
import com.example.pruebavalid.Base.Implementations.App
import com.example.pruebavalid.Base.Implementations.BaseActivity
import com.example.pruebavalid.DataAccess.RetrofitYesid.Manejador.RetrofitManager
import com.example.pruebavalid.DataAccess.RetrofitYesid.Manejador.RetrofitParcelable
import com.example.pruebavalid.DataAccess.RetrofitYesid.Recursos.Servicios
import com.example.pruebavalid.Models.ListTrack
import com.example.pruebavalid.Models.TopArtists
import com.example.pruebavalid.Presentation.Dash.FragmentListSongs.Implementations.FragmentListSongs
import com.example.pruebavalid.R

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pruebaproxy()
    }

    fun pruebaproxy(){
        RetrofitManager()
            .conContexto(App.mContext!!)
            .conObjetoAEnviar(object : RetrofitParcelable {})
            .conObjetoEsperado(TopArtists::class.java)
            .conEscuchadorListaObjetos {
                Log.e("","");
            }
            .conEscuchadorObjeto {
                Log.e("","");
            }
            .escuchadorFallas()
            .conRespuestaDeFallas { titulo, mensaje ->
                Log.e("","");
            }
            .conServicio(Servicios.list_song_topartists)
            .iniciarConsulta()
    }

    private fun navigationListSong(list: ListTrack){
        val fragment = FragmentListSongs()
        fragment.listSongs = list
        fragment.onFragmentListView
    }

    inner class onActionListSong: FragmentListSongs.OnFragmentInteractionListener {
        override fun navigationDetalleSongs() {

        }

    }
}
