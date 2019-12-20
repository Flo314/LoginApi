package com.example.loginapi.data.network.responses

import com.example.loginapi.util.ApiException
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response


/**
 * Exécutera la demande d’api et retournera la réponse
 * Traitement des erreurs
 */
abstract class SafeApiRequest {

    suspend fun<T: Any> apiRequest(call: suspend () -> Response<T>) : T {
        val response = call.invoke()

        if (response.isSuccessful) {
            return response.body()!!
        }else {
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            error?.let {
                // vérifier que la chaine est convertible en objet JSON ou non
                try {
                    // ne m'affiche pas le message d'erreur il m'affiche simplement le code d'erreur
                    message.append(JSONObject(it).getString("message"))

                    /*// m'affiche l'objet entier que l'api me renvoi
                    val parser = JsonParser()
                    var mJson: JsonElement? = null
                    mJson = parser.parse(error)
                    message.append(mJson)*/


                }catch (e: JSONException) {
                    message.append("\n")
                }
            }
            message.append("Error Code: ${response.code()}")

            // lever l'exception
            throw ApiException(message.toString())
        }
    }
}