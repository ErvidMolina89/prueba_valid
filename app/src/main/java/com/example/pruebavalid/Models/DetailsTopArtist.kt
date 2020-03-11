package com.example.pruebavalid.Models

class DetailsTopArtist : BaseModel() {
    var  name : String ?= null
    var  listeners : String ?= null
    var  mbid : String ?= null
    var  url : String ?= null
    var  streamable : String ?= null
    var  image : MutableList<Image> ?= null
}