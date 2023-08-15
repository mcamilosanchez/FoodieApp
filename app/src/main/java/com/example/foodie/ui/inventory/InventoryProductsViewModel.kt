package com.example.foodie.ui.inventory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.data.ListProductsResponse
import com.example.foodie.network.ApiService
import kotlinx.coroutines.launch
import retrofit2.Callback

class InventoryProductsViewModel : ViewModel(){

    /**Esta variable se usa cuando hay un error en la respuesta del web service en el try-catch
     * de Retrofit.**/
    private var responseRetrofit: String = ""

    val status: MutableLiveData<ListProductsResponse> =
        MutableLiveData(ListProductsResponse(status = false, emptyList(), "loading"))

    fun getListProducts(): LiveData<ListProductsResponse> {
        viewModelScope.launch {
            try {
                val response = ApiService.Api.retrofitService.getListProducts()
                val values = response.productos

                val new = values?.filter {
                    it.precioProducto.toInt() < 10000
                }

//                map
//
//                apply

//                lazy
//
//                apply
//
//                let
//
//                also
//
//                filter

                values?.let {
                    responseRetrofit = "There is no error in the query"
                    Log.i("Message ViewModelScope:", responseRetrofit)
                    //callback(response)
                    status.value = ListProductsResponse(status = true,
                        new, "successfull")
                }

                values





            }
            catch (e: Exception) {
                status.value = ListProductsResponse(status = false, emptyList(), "error")
                responseRetrofit = e.toString()
                Log.e("Error ViewModelScope:", e.toString())

            }
        }
        return status
    }
}