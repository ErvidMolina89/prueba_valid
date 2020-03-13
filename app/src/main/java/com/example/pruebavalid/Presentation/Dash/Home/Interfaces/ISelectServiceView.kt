package com.example.pruebavalid.Presentation.Dash.Home.Interfaces

import com.example.pruebavalid.Models.ListTopArtists
import com.example.pruebavalid.Models.ListTrack

interface ISelectServiceView {
    fun responseListTrack(listTracks: ListTrack)
    fun responselistTopArtists(listTopArtists: ListTopArtists)
}