package com.example.pruebavalid.DataAccess.Repositories

import com.example.pruebavalid.DataAccess.Connection.Resources.Services
import com.example.pruebavalid.Models.MessageResponse

interface IRepository {
    fun onSuccessResponse(objectResponse: Any?, services: Services)
    fun onFailedResponse(response: MessageResponse, services: Services)
}