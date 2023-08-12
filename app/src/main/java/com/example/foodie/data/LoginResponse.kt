package com.example.foodie.data

import com.google.gson.annotations.SerializedName

data class DatosUsuario(

    @SerializedName("nomUsuario")
    val nomUsuario: String,

    @SerializedName("apeUsuario")
    val apeUsuario: String

    )

data class LoginResponse(

    @SerializedName("status")
    val status: Boolean,

    //Como datos es un objeto, hacemos otra data class de DatosUsuario (como se hizo al inicio)
    @SerializedName("datos")
    val datos: DatosUsuario,

    @SerializedName("message")
    val message: String

)

