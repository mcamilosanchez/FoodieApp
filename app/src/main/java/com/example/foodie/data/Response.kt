package com.example.foodie.data

import com.google.gson.annotations.SerializedName

data class Response(

    @SerializedName("status")
    val status: Boolean,

    @SerializedName("message")
    val message: String

)
