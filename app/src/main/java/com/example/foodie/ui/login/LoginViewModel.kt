package com.example.foodie.ui.login

import android.content.SharedPreferences
import com.example.foodie.network.ApiService
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.data.LoginResponse
import com.example.foodie.data.Users
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    /**Esta variable se usa cuando hay un error en la respuesta del web service en el try-catch
     * de Retrofit.**/
    var responseRetrofit: String = ""

    fun getDataLogin(user : String, pass : String) : Boolean {

        val call: Call<Users> = ApiService.Api.retrofitService.getData()

        call.enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                if(response.isSuccessful) {
                    val data: Users? = response.body()
                    if(data != null){
                        Log.d("ResponseData", "Status: ${data.status}")
                        Log.d("ResponseData", "User: ${data.usuario}")
                        Log.d("ResponseData", "Message: ${data.message}")
                    } else {
                        Log.d("ResponseData", "Response body is null")
                    }
                } else {
                    Log.d("ResponseData", "Hubo un error en la respuesta")
                }
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                Log.d("ResponseData", "$t")
            }
        })
        return true
    }

    fun getLogin(user: String, pass: String, callback: (LoginResponse) -> Unit) {
        viewModelScope.launch {
            try {
                /** Será una corrutina por implementada en Retrofit, por lo anterior, no
                 * hay necesidad de crear dispatchers ya que Retrofit proporciona estas
                 * características. Este valor tendrá la respuesta del webService Login. **/
                val response = ApiService.Api.retrofitService.login(user, pass)
                    responseRetrofit = "No hay error en la consulta"
                callback(response)
            } catch (e: Exception) {
                responseRetrofit = e.toString()
                Log.e("Error ViewModelScope:", e.toString())
            }
        }
    }

}