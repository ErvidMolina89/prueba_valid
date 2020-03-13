package com.example.pruebavalid.Presentation.Dash.Home.Interfaces

import com.example.pruebavalid.Models.ListTopArtists
import com.example.pruebavalid.Models.ListTrack

interface ISelectServiceListener {
    fun responseListTrack(listTracks: ListTrack)
    fun responseListTopArtists(listTopArtists: ListTopArtists)
}