package com.example.pruebavalid.DataAccess.Repositories

import android.content.Context
import com.example.pruebavalid.DataAccess.Connection.Handler.IRetrofitParcelable
import com.example.pruebavalid.DataAccess.Connection.Handler.RetrofitProxy
import com.example.pruebavalid.DataAccess.Connection.Resources.Services
import com.example.pruebavalid.Models.*

class RepoService (context: Context) {

    private val context : Context
    init {
        this.context = context
    }

    fun < T: IRepository>callService(objectResponse: BaseModel, objectSend: IRetrofitParcelable, service : Services, responder : T){
        RetrofitProxy()
            .withContext(context)
            .withObjectToSend(objectSend)
            .withExpectedObjeto(objectResponse::class.java)
            .withListenerListObjetcs {
                responder.onSuccessResponse(it, service)
            }
            .withListenerObjetc {
                responder.onSuccessResponse(it, service)
            }
            .listenerFailure()
            .withAnswerOfFailure { titulo, mensaje ->
                val message = MessageResponse()
                message.Code = titulo
                message.Message = mensaje.toString()
                responder.onFailedResponse(message, service)
            }
            .withService(service)
            .startQuery()
    }
}