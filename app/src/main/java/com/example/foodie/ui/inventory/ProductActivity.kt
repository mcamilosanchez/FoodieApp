package com.example.foodie.ui.inventory

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodie.R
import com.example.foodie.data.ListProductsResponse
import com.example.foodie.data.Products
import com.example.foodie.databinding.ActivityProductBinding

class ProductActivity : AppCompatActivity() {

    private val binding: ActivityProductBinding by lazy {
        ActivityProductBinding.inflate(layoutInflater)
    }

    private val viewModel: InventoryProductsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        callProductList()
    }

    private fun callProductList() {
        viewModel.getListProducts { listProductsResponse: ListProductsResponse ->
            if (listProductsResponse.status) {
                val productList = listProductsResponse.productos
                showProducts(productList)
            } else {
                Log.i("Product list:", listProductsResponse.message)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showProducts(productList: List<Products>) {
        val adapter = ProductAdapter(productList)
        binding.rVProducts.layoutManager = LinearLayoutManager(this)
        binding.rVProducts.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}