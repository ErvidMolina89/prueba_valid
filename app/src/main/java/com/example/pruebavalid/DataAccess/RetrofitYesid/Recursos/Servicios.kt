package com.example.pruebavalid.DataAccess.RetrofitYesid.Recursos

import com.example.pruebavalid.DataAccess.RetrofitYesid.Manejador.ParametrosServicios

enum class Servicios (url : String,metodo : ParametrosServicios.Metodos) : ParametrosServicios {
    GET_LISTA_PACIENTES("ListaPacientes",ParametrosServicios.Metodos.GET),
    GET_CONTRATO("Contrato/Psicologo",ParametrosServicios.Metodos.GET),
    list_song_tracks("?method=geo.gettoptracks&country=spain&api_key=829751643419a7128b7ada50de590067&page=2&format=json",ParametrosServicios.Metodos.GET),
    list_song_topartists("?method=geo.gettopartists&country=spain&api_key=829751643419a7128b7ada50de590067&page=2&format=json",ParametrosServicios.Metodos.GET),
    ;

    private val url : String
    private val metodo : ParametrosServicios.Metodos
    init {
        this.url = url
        this.metodo = metodo
    }
    override fun getURL(): String {
        return url
    }

    override fun getMethodo(): ParametrosServicios.Metodos {
        return metodo
    }
}