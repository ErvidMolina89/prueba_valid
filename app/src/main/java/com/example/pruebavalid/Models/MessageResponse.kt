package com.example.pruebavalid.Models

/**
 * Created by exsis on 7/11/17.
 */
class MessageResponse() : BaseModel() {
    var Code: Int? = null
    var Message: String? = null

    constructor(code: Int, message: String?) : this() {
        this.Code = code
        this.Message = message
    }

}