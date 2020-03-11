package com.example.pruebavalid.DataAccess.Connection

import android.content.Context
import co.com.mitiempo.retrofitgenerico.RetrofitManager.IRetrofitParcelable
import com.example.pruebavalid.BuildConfig
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProxyRetrofit {

    private var context : Context?= null
    fun withContext(context : Context) :objectToSend{
        this.context = context
        return objectToSend()
    }

    private var listenerObjectToSend : IRetrofitParcelable?= null
    inner class objectToSend{
        fun withObjectToSend(listenerObjectToSend : IRetrofitParcelable) : expectedObjeto{
            this@ProxyRetrofit.listenerObjectToSend = listenerObjectToSend
            return expectedObjeto()
        }
    }

    private var ExpectedObjectClass : Class<*> ?= null
    inner class expectedObjeto{
        fun <T:IRetrofitParcelable> withExpectedObjeto(expectedObjeto : Class<T>) : answerObjeto{
            this@ProxyRetrofit.ExpectedObjectClass = expectedObjeto
            return answerObjeto()
        }
    }

    private var listenerAnswerObject : ((IRetrofitParcelable?)->Unit)?= null
    private var listenerListObjetc : ((MutableList<IRetrofitParcelable>?)->Unit)?= null
    inner class answerObjeto{

        fun withListenerObjetc(listenerAnswerObject : ((IRetrofitParcelable?)->Unit)?) : answerObjeto{
            this@ProxyRetrofit.listenerAnswerObject = listenerAnswerObject
            return this
        }

        fun withListenerListObjetcs(listenerListObjetc : ((MutableList<IRetrofitParcelable>?)->Unit)?) : answerObjeto{
            this@ProxyRetrofit.listenerListObjetc = listenerListObjetc
            return this
        }

        fun listenerFailure() : answerFailure{
            return answerFailure()
        }

    }

    private var listenerOfFailure : ((Int,Int)->Unit)?= null
    inner class answerFailure{
        fun withAnswerOfFailure(listenerOfFailure : ((Int,Int)->Unit)) : service{
            this@ProxyRetrofit.listenerOfFailure = listenerOfFailure
            return service()
        }
    }

    private var serviceSelected : IServiceParameters ?= null
    inner class service{
        fun withService(service : IServiceParameters) : QueryManager{
            this@ProxyRetrofit.serviceSelected = service
            return QueryManager()
        }
    }

    private val header = emptyMap<String,String>().toMutableMap()
    private val complementaryFields = emptyMap<String,String>().toMutableMap()

    inner class QueryManager{

        fun withParameterHeader(key : String, data : String) : QueryManager{
            header[key] = data
            return this
        }

        fun withComplementaryFields(key : String , data : String) : QueryManager{
            complementaryFields[key] = data
            return this
        }

        fun startQuery(){

            val retrofit = generateRetrofit()
            val genericService = retrofit.create(IGenericServices::class.java)

            val call = toSelectCall(genericService)

            call.enqueue(
                HandlerAnswerRetrofit()
                    .withListenerOfFailure(listenerOfFailure)
                    .withListenerListObjetcs(listenerListObjetc)
                    .withListenerAnswerObjetcs(listenerAnswerObject)
                    .withExpectedObject(ExpectedObjectClass)
            )

        }

        private fun generateRetrofit() : Retrofit {
//            val BaseURL = "http://192.168.0.4:3000/"
            val BaseURL = BuildConfig.Base_Url

            val gson =  GsonBuilder()
                .setLenient()
                .create();


            return Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        }

        private fun toSelectCall(genericService : IGenericServices) : Call<Any>{
            return when(serviceSelected!!.getMethod()){
                IServiceParameters.Methods.GET -> genericService.getGeneric(serviceSelected!!.getURL())
                IServiceParameters.Methods.POST -> genericService.getGeneric(serviceSelected!!.getURL())
                IServiceParameters.Methods.PUT -> genericService.getGeneric(serviceSelected!!.getURL())
                IServiceParameters.Methods.DELETE -> genericService.getGeneric(serviceSelected!!.getURL())
            }

        }

    }

}