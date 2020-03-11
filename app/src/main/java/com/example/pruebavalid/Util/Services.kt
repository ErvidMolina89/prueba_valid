package co.com.mitiempo.retrofitgenerico

import com.example.pruebavalid.DataAccess.Connection.IServiceParameters

enum class Services (url : String,
                     method : IServiceParameters.Methods)
    : IServiceParameters {

    list_songs("?method=geo.gettoptracks&country=spain&api_key=829751643419a7128b7ada50de590067&page=2&format=json",IServiceParameters.Methods.GET)
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

    override fun getMethod(): IServiceParameters.Methods {
        return method
    }
}