package com.example.pruebavalid.Presentation.Dash.FragmentListSongs.Interfaces

import com.example.pruebavalid.Models.ListTrack

interface IFragmentListSongsListener {
    fun receiveListSongsService(listSongs: ListTrack)
}