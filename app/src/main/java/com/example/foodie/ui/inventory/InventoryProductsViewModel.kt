package com.example.foodie.ui.inventory

import androidx.lifecycle.*
import com.example.foodie.data.ListProductsResponse
import com.example.foodie.network.ApiService
import com.example.foodie.utils.Resource
import com.example.foodie.utils.Status
import kotlinx.coroutines.launch

class InventoryProductsViewModel : ViewModel(){

    /**Esta variable se usa cuando hay un error en la respuesta del web service en el try-catch
     * de Retrofit.**/
    private var responseRetrofit: String = ""

    val status: MutableLiveData<Resource<ListProductsResponse>> =
        MutableLiveData(Resource.loading(data = null))

    fun getListProducts(): LiveData<Resource<ListProductsResponse>> {
        viewModelScope.launch {
            try {
                val response = ApiService.Api.retrofitService.getListProducts()
                response.productos?.let {
                    status.value = Resource(Status.SUCCESS, response, "Data was loaded successfully" )

                    //Example to do filter by price
//                    val productsFilter = response.productos.filter {
//                        it.precioProducto.toInt() < 10000
//                    }
//                    val newResponse = ListProductsResponse(status = true, productsFilter, message = "Success")
//                    status.value = Resource(Status.SUCCESS, newResponse, "Data was loaded successfully")
                }

            }
            catch (e: Exception) {
                status.value = Resource(Status.ERROR, null, "Error")
            }
        }
        return status
    }
}