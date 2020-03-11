package com.example.pruebavalid.DataAccess.RetrofitYesid.Manejador

import android.content.Context
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager {


    private var context : Context ?= null
    fun conContexto(context : Context) :objetoAEnviar{
        this.context = context
        return objetoAEnviar()
    }

    private var escuchadorObjetoAEnviar : RetrofitParcelable ?= null
    inner class objetoAEnviar{
        fun conObjetoAEnviar(escuchadorObjetoAEnviar : RetrofitParcelable) : objetoEsperado{
            this@RetrofitManager.escuchadorObjetoAEnviar = escuchadorObjetoAEnviar
            return objetoEsperado()
        }
    }

    private var claseObjetoEperado : Class<*> ?= null
    inner class objetoEsperado{
        fun <T:RetrofitParcelable> conObjetoEsperado(objetoEsperado : Class<T>) : respuestaObjeto{
            this@RetrofitManager.claseObjetoEperado = objetoEsperado
            return respuestaObjeto()
        }
    }

    private var escuchadorRespuestaObjeto : ((RetrofitParcelable?)->Unit)?= null
    private var escuchadorListaObjetos : ((MutableList<RetrofitParcelable>?)->Unit)?= null
    inner class respuestaObjeto{

        fun conEscuchadorObjeto(escuchadorRespuestaObjeto : ((RetrofitParcelable?)->Unit)?) : respuestaObjeto{
            this@RetrofitManager.escuchadorRespuestaObjeto = escuchadorRespuestaObjeto
            return this
        }

        fun conEscuchadorListaObjetos(escuchadorListaObjetos : ((MutableList<RetrofitParcelable>?)->Unit)?) : respuestaObjeto{
            this@RetrofitManager.escuchadorListaObjetos = escuchadorListaObjetos
            return this
        }

        fun escuchadorFallas() : respuestaFalla{
            return respuestaFalla()
        }

    }

    private var escuchadorDeFallas : ((Int,Int)->Unit)?= null
    inner class respuestaFalla{
        fun conRespuestaDeFallas(escuchadorDeFallas : ((Int,Int)->Unit)) : servicio{
            this@RetrofitManager.escuchadorDeFallas = escuchadorDeFallas
            return servicio()
        }
    }

    private var servicioSeleccionado : ParametrosServicios ?= null
    inner class servicio{
        fun conServicio(servicio : ParametrosServicios) : ManejadorConsulta{
            this@RetrofitManager.servicioSeleccionado = servicio
            return ManejadorConsulta()
        }
    }

    private val cabezera = emptyMap<String,String>().toMutableMap()
    private val camposComplementarios = emptyMap<String,String>().toMutableMap()

    inner class ManejadorConsulta{

        fun conParametroCabecera(llave : String,valor : String) : ManejadorConsulta{
            cabezera[llave] = valor
            return this
        }

        fun conCamposComplementarios(llave : String , valor : String) : ManejadorConsulta{
            camposComplementarios[llave] = valor
            return this
        }

        fun iniciarConsulta(){

            val retrofit = generarRetrofit()
            val serviciosGenericos = retrofit.create(ServiciosGenericos::class.java)

            val call = seleccionarCall(serviciosGenericos)

            call.enqueue(
                ManejadorRespuestasRetrofit()
                    .conEscuchadorFallas(escuchadorDeFallas)
                    .conEscuchadorListaObjetos(escuchadorListaObjetos)
                    .conEscuchadorRespuestaObjeto(escuchadorRespuestaObjeto)
                    .conObjetoEsperado(claseObjetoEperado)
            )

        }

        private fun generarRetrofit() : Retrofit{
//            val BaseURL = "http://192.168.0.4:3000/"
            val BaseURL = "http://ws.audioscrobbler.com/2.0/"

            val gson =  GsonBuilder()
                .setLenient()
                .create();


            return Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        }

        private fun seleccionarCall(serviciosGenericos : ServiciosGenericos) : Call<Any>{
            return when(servicioSeleccionado!!.getMethodo()){
                ParametrosServicios.Metodos.GET -> serviciosGenericos.getGenerico(servicioSeleccionado!!.getURL())
                ParametrosServicios.Metodos.POST -> serviciosGenericos.getGenerico(servicioSeleccionado!!.getURL())
                ParametrosServicios.Metodos.PUT -> serviciosGenericos.getGenerico(servicioSeleccionado!!.getURL())
                ParametrosServicios.Metodos.DELETE -> serviciosGenericos.getGenerico(servicioSeleccionado!!.getURL())
            }

        }

    }



}