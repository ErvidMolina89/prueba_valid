package com.example.pruebavalid.Presentation.Dash.FragmentListSongs.Implementations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebavalid.Base.Implementations.App
import com.example.pruebavalid.Base.Implementations.BaseFragment
import com.example.pruebavalid.Models.DetailsTopArtist
import com.example.pruebavalid.Models.DetailsTrack
import com.example.pruebavalid.Models.ListTopArtists
import com.example.pruebavalid.Models.ListTrack
import com.example.pruebavalid.Presentation.Dash.FragmentListSongs.Complements.RecyclerAdapterArtists
import com.example.pruebavalid.Presentation.Dash.FragmentListSongs.Complements.RecyclerAdapterSongs
import com.example.pruebavalid.Presentation.Dash.FragmentListSongs.Interfaces.IFragmentListSongsPresenter
import com.example.pruebavalid.Presentation.Dash.FragmentListSongs.Interfaces.IFragmentListSongsView
import com.example.pruebavalid.R
import kotlinx.android.synthetic.main.fragment_list_songs.*

class FragmentListSongs : BaseFragment(){

    var listTracks: ListTrack? = null
    var listTopArtists: ListTopArtists? = null
    var typeService: Int? = null
    private var presenter: IFragmentListSongsPresenter? = null
    var onFragmentListView = onActionFragmentListView()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.fragment_list_songs, container, false)
    }

    override fun onResume() {
        super.onResume()
        createListeners()
    }

    private fun createListeners() {
        when(typeService){
            1 -> {
                rv_list_songs.post {
                    rv_list_songs.adapter = null
                    rv_list_songs.setHasFixedSize(true)
                    rv_list_songs.layoutManager = LinearLayoutManager(App.mContext)
                    rv_list_songs.adapter = RecyclerAdapterSongs(
                        listTracks!!.tracks!!.track!!,
                        onActionRecyclerSongs()
                    )
                }
            }
            2 -> {
                rv_list_songs.post {
                    tv_title_song.text = baseActivity?.getText(R.string.title_artists)
                    rv_list_songs.adapter = null
                    rv_list_songs.setHasFixedSize(true)
                    rv_list_songs.layoutManager = LinearLayoutManager(App.mContext)
                    rv_list_songs.adapter = RecyclerAdapterArtists(
                        listTopArtists!!.topartists!!.artist!!,
                        onActionRecyclerArtists()
                    )
                }
            }
        }
    }

    inner class onActionFragmentListView: IFragmentListSongsView {
        override fun receiveListSongsService(listSongs: ListTrack) {
            try {

            }catch (e: Exception) {

            }
        }

    }

    inner class onActionRecyclerSongs: RecyclerAdapterSongs.SonsListener {
        override fun onPressCardSong(song: DetailsTrack) {

        }
    }

    inner class onActionRecyclerArtists: RecyclerAdapterArtists.ArtistsListener {
        override fun onPressCardArtists(song: DetailsTopArtist) {

        }
    }

    interface OnFragmentInteractionListener {
        fun navigationDetalleSongs()
    }

    companion object {
        fun newInstance() =
            FragmentListSongs().apply {
                arguments = Bundle().apply {}
            }
    }
}