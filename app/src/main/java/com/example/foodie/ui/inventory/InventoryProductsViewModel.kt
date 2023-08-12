package com.example.foodie.ui.inventory

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.data.ListProductsResponse
import com.example.foodie.network.ApiService
import kotlinx.coroutines.launch
import retrofit2.Callback

class InventoryProductsViewModel : ViewModel(){

    /**Esta variable se usa cuando hay un error en la respuesta del web service en el try-catch
     * de Retrofit.**/
    var responseRetrofit: String = ""

    fun getListProducts(callback: (ListProductsResponse) -> Unit) {
        viewModelScope.launch {
            try {
                val response = ApiService.Api.retrofitService.getListProducts()
                responseRetrofit = "There is no error in the query"
                Log.i("Message ViewModelScope:", responseRetrofit)
                callback(response)
            } catch (e: Exception) {
                responseRetrofit = e.toString()
                Log.e("Error ViewModelScope:", e.toString())
            }
        }
    }
}