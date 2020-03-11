package com.example.pruebavalid.Models

import com.google.gson.annotations.SerializedName

class DetailsTrack : BaseModel() {
    var  name : String ?= null
    var  duration : String ?= null
    var  listeners : String ?= null
    var  mbid : String ?= null
    var  url : String ?= null
    var  streamable : Streamable ?= null
    var  artist : Artist ?= null
    var  image : MutableList<Image> ?= null

    @SerializedName("@attr ")
    var  attr : Rank ?= null
}