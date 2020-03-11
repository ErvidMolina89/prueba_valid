package com.example.pruebavalid.Presentation.Dash.FragmentListSongs.Implementations

import com.example.pruebavalid.Models.ListTrack
import com.example.pruebavalid.Presentation.Dash.FragmentListSongs.Interfaces.IFragmentListSongsBL
import com.example.pruebavalid.Presentation.Dash.FragmentListSongs.Interfaces.IFragmentListSongsListener
import com.example.pruebavalid.Presentation.Dash.FragmentListSongs.Interfaces.IFragmentListSongsPresenter
import com.example.pruebavalid.Presentation.Dash.FragmentListSongs.Interfaces.IFragmentListSongsView

class FragmentListSongsPresenter(var view: IFragmentListSongsView) : IFragmentListSongsPresenter{

    var bl: IFragmentListSongsBL? = null

    init {
        bl = FragmentListSongsBL(ActionReturnBL())
    }


    override fun getListSongsService() {
        bl?.getListSongsService()
    }

    inner class ActionReturnBL: IFragmentListSongsListener {
        override fun receiveListSongsService(listSongs: ListTrack) {
            view.receiveListSongsService(listSongs)
        }

    }
}