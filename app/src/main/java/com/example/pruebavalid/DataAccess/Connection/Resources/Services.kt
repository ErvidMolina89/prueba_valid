package com.example.pruebavalid.DataAccess.Connection.Resources

import com.example.pruebavalid.DataAccess.Connection.Handler.IServiceParameters

enum class Services (url : String,
                     method : IServiceParameters.Methods)
    : IServiceParameters {

    list_song_tracks("?method=geo.gettoptracks&country=spain&api_key=829751643419a7128b7ada50de590067&page=2&format=json",IServiceParameters.Methods.GET),
    list_song_topartists("?method=geo.gettopartists&country=spain&api_key=829751643419a7128b7ada50de590067&page=2&format=json",IServiceParameters.Methods.GET),
    LogIn("",IServiceParameters.Methods.GET),
    registerUser("",IServiceParameters.Methods.GET),
    ;

    private val url : String
    private val method : IServiceParameters.Methods

    init {
        this.url = url
        this.method = method
    }

    override fun getURL(): String {
        return url
    }

    override fun getMethods(): IServiceParameters.Methods {
        return method
    }
}