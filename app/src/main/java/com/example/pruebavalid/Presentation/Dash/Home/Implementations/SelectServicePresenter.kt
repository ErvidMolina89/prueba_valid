package com.example.pruebavalid.Presentation.Dash.Home.Implementations

import android.content.Context
import com.example.pruebavalid.DataAccess.Connection.Handler.IRetrofitParcelable
import com.example.pruebavalid.DataAccess.Connection.Resources.Services
import com.example.pruebavalid.Models.BaseModel
import com.example.pruebavalid.Models.ListTopArtists
import com.example.pruebavalid.Models.ListTrack
import com.example.pruebavalid.Presentation.Dash.Home.Interfaces.ISelectServiceBL
import com.example.pruebavalid.Presentation.Dash.Home.Interfaces.ISelectServiceListener
import com.example.pruebavalid.Presentation.Dash.Home.Interfaces.ISelectServicePresenter
import com.example.pruebavalid.Presentation.Dash.Home.Interfaces.ISelectServiceView

class SelectServicePresenter (context: Context, view : ISelectServiceView):
    ISelectServicePresenter {

    private val selectServiceView : ISelectServiceView
    private val context : Context
    private val selectServiceBL : ISelectServiceBL
    init {
        this.context = context
        this.selectServiceView = view
        selectServiceBL = SelectServiceBL(context,Listener())
    }

    override fun callService(
        objectResponse: BaseModel,
        objectSend: IRetrofitParcelable,
        service: Services
    ) {
        selectServiceBL.callService(objectResponse, objectSend, service)
    }



    private inner class Listener : ISelectServiceListener {
        override fun responseListTrack(listTracks: ListTrack) {
            selectServiceView.responseListTrack(listTracks)
        }

        override fun responseListTopArtists(listTopArtists: ListTopArtists) {
            selectServiceView.responselistTopArtists(listTopArtists)
        }

    }
}