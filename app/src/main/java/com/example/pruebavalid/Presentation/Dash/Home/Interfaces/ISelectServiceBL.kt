package com.example.pruebavalid.Presentation.Dash.Home.Interfaces

import com.example.pruebavalid.DataAccess.Connection.Handler.IRetrofitParcelable
import com.example.pruebavalid.DataAccess.Connection.Resources.Services
import com.example.pruebavalid.Models.BaseModel

interface ISelectServiceBL {
    fun callService(objectResponse: BaseModel, objectSend: IRetrofitParcelable, service: Services)
}