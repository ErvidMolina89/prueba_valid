package com.example.pruebavalid.Presentation.Dash.FragmentListSongs.Complements

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebavalid.Base.Implementations.App
import com.example.pruebavalid.Models.DetailsTopArtist
import com.example.pruebavalid.Models.DetailsTrack
import com.example.pruebavalid.Models.Track
import com.example.pruebavalid.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class RecyclerAdapterArtists (
    val listArtists : MutableList<DetailsTopArtist>,
    val listener  : ArtistsListener? = null
): RecyclerView.Adapter<RecyclerAdapterArtists.SongsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsHolder {
        val view   = LayoutInflater.from(App.mContext).inflate(R.layout.card_song, null, false)
        val params = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT)
        view.layoutParams = params

        return SongsHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return listArtists.size
    }

    override fun onBindViewHolder(holder: SongsHolder, position: Int) {
        holder.setDetalleSongs(listArtists[position])
    }


    class SongsHolder(
        val view: View,
        val mListener: ArtistsListener?
        ): RecyclerView.ViewHolder(view){

        val cardViewSongs   = view.findViewById<CardView>(R.id.card_song)
        val image           = view.findViewById<CircleImageView>(R.id.civ_card_image)
        val name            = view.findViewById<TextView>(R.id.tv_name_song)
        val duration        = view.findViewById<TextView>(R.id.tv_duration_song)
        val listeners    = view.findViewById<TextView>(R.id.tv_listeners)
        val nameArtist      = view.findViewById<TextView>(R.id.tv_artist_name)
        val rank            = view.findViewById<TextView>(R.id.tv_rank)

        fun setDetalleSongs(artist: DetailsTopArtist){
            if (artist.image?.get(2)?.text != null) convertImageService(artist.image!![2].text)
            if (artist.name != null) name.text = artist.name
            if (artist.listeners != null) listeners?.text = artist.listeners
            duration.visibility = View.GONE
            nameArtist.visibility = View.GONE
            rank.visibility = View.GONE

            cardViewSongs.setOnClickListener {
                mListener?.onPressCardArtists(artist)
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

    interface ArtistsListener {
        fun onPressCardArtists(artist: DetailsTopArtist)

    }
}