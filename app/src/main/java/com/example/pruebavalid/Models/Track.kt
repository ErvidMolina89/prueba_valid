package com.example.pruebavalid.Models

import com.google.gson.annotations.SerializedName

class Track : BaseModel() {

    var track : MutableList<DetailsTrack>? = null

    @SerializedName("@attr")
    var attr : Attr ?= null


}