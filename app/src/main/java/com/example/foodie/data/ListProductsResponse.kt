package com.example.foodie.data

import com.google.gson.annotations.SerializedName

data class Products(

    @SerializedName("idProducto")
    val idProducto: String,

    @SerializedName("codProducto")
    val codProducto: String,

    @SerializedName("nomProducto")
    val nomProducto: String,

    @SerializedName("descProducto")
    val descProducto: String,

    @SerializedName("precioProducto")
    val precioProducto: String,

    @SerializedName("imageProducto")
    val imageProducto: String

)

data class ListProductsResponse(

    @SerializedName("status")
    val status: Boolean,

    @SerializedName("productos")
    val productos: List<Products>,

    @SerializedName("message")
    val message: String,

)