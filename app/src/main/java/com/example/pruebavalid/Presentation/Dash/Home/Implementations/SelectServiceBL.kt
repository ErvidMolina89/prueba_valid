package com.example.pruebavalid.Presentation.Dash.Home.Implementations

import android.content.Context
import com.example.pruebavalid.DataAccess.Connection.Handler.IRetrofitParcelable
import com.example.pruebavalid.DataAccess.Connection.Resources.Services
import com.example.pruebavalid.DataAccess.Repositories.IRepository
import com.example.pruebavalid.DataAccess.Repositories.RepoService
import com.example.pruebavalid.Models.BaseModel
import com.example.pruebavalid.Models.ListTopArtists
import com.example.pruebavalid.Models.ListTrack
import com.example.pruebavalid.Models.MessageResponse
import com.example.pruebavalid.Presentation.Dash.Home.Interfaces.ISelectServiceBL
import com.example.pruebavalid.Presentation.Dash.Home.Interfaces.ISelectServiceListener

class SelectServiceBL (context: Context, ISelectServiceListener : ISelectServiceListener) :
    ISelectServiceBL {

    private val context : Context
    private val listener : ISelectServiceListener

    init {
        this.context = context
        this.listener = ISelectServiceListener
    }

    override fun callService(
        objectResponse: BaseModel,
        objectSend: IRetrofitParcelable,
        service: Services
    ) {
        RepoService(context).callService(objectResponse, objectSend, service, ListenerRepositories())
    }

    private inner class ListenerRepositories : IRepository{

        override fun onSuccessResponse(objectResponse: Any?, services: Services) {
            when(services){
                Services.list_song_tracks  -> {
                    listener.responseListTrack(objectResponse as ListTrack)
                }
                Services.list_song_topartists  -> {
                    listener.responseListTopArtists(objectResponse as ListTopArtists)
                }
                else -> {  }
            }
        }

        override fun onFailedResponse(response: MessageResponse, services: Services) {
            when(services){
                Services.list_song_tracks   -> {  }
                Services.list_song_topartists   -> {  }
                else -> {  }
            }
        }

    }



}