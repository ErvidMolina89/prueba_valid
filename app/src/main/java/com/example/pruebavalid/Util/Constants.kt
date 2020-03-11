package com.example.pruebavalid.Util

class Constants {

    object Url{

        const val URL_BASE: String = "https://chat-exsis.herokuapp.com/"

        //Loguea un usuario en la app
        const val POST_LOGIN: String = "api/signin"
        //Trae las sedes por defecto
        const val GET_USERS: String = "api/getusers"

    }
    //Identificador del tipo de consulta
    enum class consulta(val url:String, val method: String, val array: Boolean){
        INICIARSECION(
            Url.POST_LOGIN,
            HTTPMethod.POST, false),
        GET_USERS(
            Url.GET_USERS,
            HTTPMethod.GET, false),

        ;
    } object ServiceTags {
        const val SONGS: Int = 0
    }

    enum class TiemposEspera(val tiempo: Long){
        SOLICITUD_SERVICIO(40000),
        ESPERA_HISTORIAL(500)
    }

    object HTTPMethod{
        const val POST: String = "post"
        const val GET: String = "Get"
        const val PUT: String = "put"
        const val TIMEOUT: Long = 20
    }

    object HttpCodes {
        const val OK: Int = 200
        const val UNAUTHORIZED: Int = 401
    }

    enum class TypeConnectionInternet {
        WIFI,
        DATOS_MOVILES,
        CONNECT,
        SIN_CONEXION,
    }

    enum class TagsDialogue(val typeDialogue: TypeDialogue, val etiquetas: String) {
        USER_CREATE(TypeDialogue.INFORMACION, "User create"),
        USER_EXISTS(TypeDialogue.INFORMACION, "User exists"),
        ERROR(TypeDialogue.INFORMACION, "Error"),
        CODE_INVALID(TypeDialogue.INFORMACION, "Codigo expirado"),
        WRONG_PASSWORD(TypeDialogue.INFORMACION, "Error cambio contraseña"),
        INTERNET(TypeDialogue.ERROR_INTERNET, "Error sin Internet")
    }
}