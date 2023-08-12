package com.example.foodie.data

import com.google.gson.annotations.SerializedName

data class Usuario(
    @SerializedName("codUsuario")
    val codUsuario: Int,

    @SerializedName("nomUsuario")
    val nomUsuario: String,

    @SerializedName("apeUsuario")
    val apeUsuario: String,

    @SerializedName("cedUsuario")
    val cedUsuario: String,

    @SerializedName("codPerfil")
    val codPerfil: String,

    @SerializedName("usrCreacion")
    val usrCreacion: String,

    @SerializedName("fecCreacion")
    val fecCreacion: String,

    @SerializedName("usrEdicion")
    val usrEdicion: String,

    @SerializedName("fecEdicion")
    val fecEdicion: String

)

data class Users(

    @SerializedName("status")
    val status: String,

    //Como Usuario es un objeto, hacemos otra data class de Usuario (como se hizo al inicio)
    @SerializedName("usuario")
    val usuario: Usuario,

    @SerializedName("message")
    val message: String

)

