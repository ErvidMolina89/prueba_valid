package com.example.pruebavalid.Presentation.Dash.Home.Implementations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pruebavalid.Base.Implementations.App
import com.example.pruebavalid.Base.Implementations.BaseFragment
import com.example.pruebavalid.DataAccess.Connection.Handler.IRetrofitParcelable
import com.example.pruebavalid.DataAccess.Connection.Handler.RetrofitProxy
import com.example.pruebavalid.DataAccess.Connection.Resources.Services
import com.example.pruebavalid.Models.BaseModel
import com.example.pruebavalid.Models.ListTopArtists
import com.example.pruebavalid.Models.ListTrack
import com.example.pruebavalid.Models.User
import com.example.pruebavalid.Presentation.Dash.Home.Interfaces.ISelectServicePresenter
import com.example.pruebavalid.Presentation.Dash.Home.Interfaces.ISelectServiceView
import com.example.pruebavalid.R
import kotlinx.android.synthetic.main.fragment_select_service.*

class SelectServiceView: BaseFragment() {

    private var viewPrincial : View ?= null
    private var presenter: ISelectServicePresenter? = null
    private var actionPresenter = actionViewPresenter()
    var listenerFragmentSelectService: OnFragmentInteractionListener? = null
    private var user : User? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewPrincial = inflater.inflate(R.layout.fragment_select_service,null,false)
        presenter = SelectServicePresenter(App.mContext!!, actionPresenter)
        return viewPrincial
    }

    override fun onResume() {
        super.onResume()
        btn_select_service_tracks.setOnClickListener{
            callService(ListTrack(), object: IRetrofitParcelable{}, Services.list_song_tracks)
        }
        btn_select_service_topArtist.setOnClickListener{
            callService(ListTopArtists(), object: IRetrofitParcelable{}, Services.list_song_topartists)
        }
    }

    private fun callService(objectResponse: BaseModel,
                            objectSend: IRetrofitParcelable,
                            service: Services
    ){
        presenter?.callService(objectResponse, objectSend, service)
    }

    inner class actionViewPresenter: ISelectServiceView {
        override fun responseListTrack(listTracks: ListTrack) {
            listenerFragmentSelectService?.callViewListServiceTrack(listTracks)
        }

        override fun responselistTopArtists(listTopArtists: ListTopArtists) {
            listenerFragmentSelectService?.callViewListServiceTopArtists(listTopArtists)
        }

    }

    interface OnFragmentInteractionListener {
        fun callViewListServiceTrack(listTracks: ListTrack)
        fun callViewListServiceTopArtists(listTopArtists: ListTopArtists)

    }

    companion object {
        fun newInstance() =
            SelectServiceView().apply {
                arguments = Bundle().apply {}
            }
    }
}