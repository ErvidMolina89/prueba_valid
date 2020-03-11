package com.example.pruebavalid.Presentation.Dash.FragmentListSongs.Implementations

import com.example.pruebavalid.Models.*
import com.example.pruebavalid.Presentation.Dash.FragmentListSongs.Interfaces.IFragmentListSongsBL
import com.example.pruebavalid.Presentation.Dash.FragmentListSongs.Interfaces.IFragmentListSongsListener

class FragmentListSongsBL (var listener: IFragmentListSongsListener): IFragmentListSongsBL{

    override fun getListSongsService() {
//        listener.receiveListSongsService(listSongs())
    }

    /*
    Funciones que proviene de mi Iresponder
     */
    override fun onSuccessResponse(objectResponse: Any?, serviceTag: Int) {


    }

    override fun onFailedResponse(response: MessageResponse, serviceTag: Int) {


    }

//    private fun listSongs(): ListTrack{
//        val list = ListTrack()
//
//        val track1          = Track()
//        track1.name         = "Crystalised"
//        track1.duration     = "201"
//        track1.listeners    = "1016948"
//        track1.mbid         = "28565ecb-6f65-4f57-ab7f-1846d643c1d7"
//        track1.url          = "https://www.last.fm/music/The+xx/_/Crystalised"
//
//        val streamable          = Streamable()
//        streamable.text         = "0"
//        streamable.fulltrack    = "0"
//
//        track1.streamable   = streamable
//
//        val artist  = Artist()
//        artist.name = "The xx"
//        artist.mbid = "c5c2ea1c-4bde-4f4d-bd0b-47b200bf99d6"
//        artist.url  = "https://www.last.fm/music/The+xx"
//
//        track1.artist       = artist
//
//        val listImage = ArrayList<Image>()
//        val image1 = Image()
//        image1.text = "https://lastfm.freetls.fastly.net/i/u/34s/2a96cbd8b46e442fc41c2b86b821562f.png"
//        image1.size = "small"
//        val image2 = Image()
//        image2.text = "https://lastfm.freetls.fastly.net/i/u/64s/2a96cbd8b46e442fc41c2b86b821562f.png"
//        image2.size = "medium"
//        val image3 = Image()
//        image3.text = "https://lastfm.freetls.fastly.net/i/u/174s/2a96cbd8b46e442fc41c2b86b821562f.png"
//        image3.size = "large"
//        val image4 = Image()
//        image4.text = "https://lastfm.freetls.fastly.net/i/u/300x300/2a96cbd8b46e442fc41c2b86b821562f.png"
//        image4.size = "extralarge"
//        listImage.add(image1)
//        listImage.add(image2)
//        listImage.add(image3)
//        listImage.add(image4)
//
//        track1.image       = listImage
//
//        val rank = Rank()
//        rank.rank = "1"
//
//        val track2          = Track()
//        track2.name         = "The Scientist"
//        track2.duration     = "309"
//        track2.listeners    = "1658781"
//        track2.mbid         = "13f5488d-8e41-42d8-9fe9-a5295f1a9a3d"
//        track2.url          = "https://www.last.fm/music/Coldplay/_/The+Scientist"
//        track2.streamable   = streamable
//
//        val artist1  = Artist()
//        artist1.name = "Coldplay"
//        artist1.mbid = "cc197bad-dc9c-440d-a5b5-d52ba2e14234"
//        artist1.url  = "https://www.last.fm/music/Coldplay"
//
//        track2.artist       = artist1
//        track2.image       = listImage
//
//        val rank1 = Rank()
//        rank1.rank = "1"
//
//        track2.attr       = rank1
//
//        val attr = Attr()
//        attr.country    = "Spain"
//        attr.page       = "2"
//        attr.perPage    = "50"
//        attr.totalPages = "137598"
//        attr.total      = "6879866"
//
//        val tracks = ArrayList<Track>()
//        tracks.add(track1)
//        tracks.add(track2)
//
//        list.tracks = tracks
//        list.attr   =attr
//
//        return list
//    }

}