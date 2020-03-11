package com.example.pruebavalid.DataAccess.Connection

import com.example.pruebavalid.Models.MessageResponse

/**
 * Created by exsis on 7/11/17.
 */
interface IResponder {
    fun onSuccessResponse(objectResponse: Any?, serviceTag: Int)
    fun onFailedResponse(response: MessageResponse, serviceTag: Int)
}