package com.example.pruebavalid.Models

import com.google.gson.annotations.SerializedName

class Streamable  : BaseModel(){

    @SerializedName("#text")
    var text : String ?= null
    var fulltrack : String ?= null
}