package com.example.foodie.utils

import org.json.JSONObject
import retrofit2.HttpException

fun handleErrorAPI(exception: HttpException): String{
    val message: String?
    message = if(exception.code() != 500){
        val jsonObject: JSONObject = JSONObject(exception.response()?.errorBody()?.string())
        "${jsonObject.opt("message")}"
    }
    else{
        "Error interno en el servidor, estamos trabajando en ello."
    }
    return message
}