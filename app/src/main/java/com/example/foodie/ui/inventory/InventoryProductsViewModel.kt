package com.example.foodie.ui.inventory

import androidx.lifecycle.*
import com.example.foodie.data.ListProductsResponse
import com.example.foodie.data.Products
import com.example.foodie.data.Response
import com.example.foodie.network.ApiService
import com.example.foodie.utils.Resource
import com.example.foodie.utils.Status
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class InventoryProductsViewModel : ViewModel(){

    /**Esta variable se usa cuando hay un error en la respuesta del web service en el try-catch
     * de Retrofit.**/
    private var responseRetrofit: String = ""

    val status: MutableLiveData<Resource<ListProductsResponse>> =
        MutableLiveData(Resource.loading(data = null))

    val productLiveData: MutableLiveData<Resource<Response>> =
        MutableLiveData(Resource.loading(data = null))

    private var productLiveData2: Resource<Response> = Resource(status = Status.LOADING,
        data = null, message = null)

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

    fun modProduct(product: Products) : Resource<Response> {
        viewModelScope.launch {
            try {
                val postProduct = ApiService.Api.retrofitService.modProduct(
                    product.idProducto,
                    product.nomProducto,
                    product.descProducto,
                    "123456")

                postProduct?.let {
                    productLiveData2 = Resource(Status.SUCCESS,
                        postProduct,
                        "Data was changed successfully" )
                }
            }
            catch (e: Exception) {
                productLiveData2 = Resource(Status.ERROR, null, "Error")
            }
        }
        return productLiveData2
    }
}