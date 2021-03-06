package com.example.pruebavalid.Presentation.Dash.FragmentListSongs.Complements

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebavalid.Base.Implementations.App
import com.example.pruebavalid.Models.DetailsTrack
import com.example.pruebavalid.Models.Track
import com.example.pruebavalid.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.imageURI

class RecyclerAdapterSongs (
    val listSongs : MutableList<DetailsTrack>,
    val listener  : SonsListener? = null
): RecyclerView.Adapter<RecyclerAdapterSongs.SongsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsHolder {
        val view   = LayoutInflater.from(App.mContext).inflate(R.layout.card_song, null, false)
        val params = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT)
        view.layoutParams = params

        return SongsHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return listSongs.size
    }

    override fun onBindViewHolder(holder: SongsHolder, position: Int) {
        holder.setDetalleSongs(listSongs[position])
    }


    class SongsHolder(
        val view: View,
        val mListener: SonsListener?
        ): RecyclerView.ViewHolder(view){

        val cardViewSongs   = view.findViewById<CardView>(R.id.card_song)
        val image           = view.findViewById<CircleImageView>(R.id.civ_card_image)
        val name            = view.findViewById<TextView>(R.id.tv_name_song)
        val duration        = view.findViewById<TextView>(R.id.tv_duration_song)
        val listenerSong    = view.findViewById<TextView>(R.id.tv_listeners)
        val nameArtist      = view.findViewById<TextView>(R.id.tv_artist_name)
        val rank            = view.findViewById<TextView>(R.id.tv_rank)

        fun setDetalleSongs(song: DetailsTrack){
            if (song.image?.get(2)?.text != null) convertImageService(song.image!![2].text)
            if (song.name != null) name.text = song.name
            if (song.duration != null) duration.text = (song.duration!!.toInt()/60).toString()
            if (song.listeners != null) listenerSong?.text = song.listeners
            if (song.artist?.name != null) nameArtist?.text = song.artist?.name
            if (song.attr?.rank != null) rank?.text = song.attr?.rank


            cardViewSongs.setOnClickListener {
                mListener?.onPressCardSong(song)
            }
        }

        private fun convertImageService(url: String?){
            try {
                    Picasso.get()
                        .load(url)
                        .centerCrop()
                        .resize(100, 100)
                        .into(image)
            }catch (e: Exception){

            }
        }
    }

    interface SonsListener {
        fun onPressCardSong(song: DetailsTrack)

    }
}