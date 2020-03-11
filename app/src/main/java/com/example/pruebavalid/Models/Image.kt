package com.example.pruebavalid.Models

import com.google.gson.annotations.SerializedName

class Image : BaseModel() {
    @SerializedName("#text")
    var text:  String ?= null
    var size:  String ?= null
}