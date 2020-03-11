package com.example.pruebavalid.Presentation.Dash.FragmentListSongs.Implementations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebavalid.Base.Implementations.App
import com.example.pruebavalid.Base.Implementations.BaseFragment
import com.example.pruebavalid.Models.ListTrack
import com.example.pruebavalid.Presentation.Dash.FragmentListSongs.Interfaces.IFragmentListSongsPresenter
import com.example.pruebavalid.Presentation.Dash.FragmentListSongs.Interfaces.IFragmentListSongsView
import com.example.pruebavalid.R
import kotlinx.android.synthetic.main.fragment_list_songs.*

class FragmentListSongs : BaseFragment(){

    var listSongs: ListTrack? = null
    var presenter: IFragmentListSongsPresenter? = null
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
        presenter = FragmentListSongsPresenter(onFragmentListView)
        getListSongs()
    }

    private fun getListSongs(){
        presenter?.getListSongsService()
    }

    inner class onActionFragmentListView: IFragmentListSongsView {
        override fun receiveListSongsService(listSongs: ListTrack) {
            try {

            }catch (e: Exception) {

            }
        }

    }

    private fun recyclerUsers(songs: ListTrack){
        rv_list_songs.post {
            rv_list_songs.adapter = null
            rv_list_songs.setHasFixedSize(true)
            rv_list_songs.layoutManager = LinearLayoutManager(App.mContext)
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