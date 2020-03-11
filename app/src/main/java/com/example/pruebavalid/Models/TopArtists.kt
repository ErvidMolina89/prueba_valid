package com.example.pruebavalid.Models

import com.google.gson.annotations.SerializedName

class TopArtists : BaseModel() {

    var artist : MutableList<DetailsTopArtist>? = null

    @SerializedName("@attr")
    var attr : Attr ?= null


}