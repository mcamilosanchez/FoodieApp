package com.example.foodie.ui.inventory

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodie.data.ListProductsResponse
import com.example.foodie.data.Products
import com.example.foodie.databinding.FragmentInventoryProductsBinding
import com.example.foodie.ui.CustomDialog

class FragmentInventoryProducts : Fragment() {

    private lateinit var binding: FragmentInventoryProductsBinding
    private val viewModel: InventoryProductsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentInventoryProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callProductsList()
    }

    private fun callProductsList() {
        val loadingDialog = CustomDialog(requireContext())
        val confirmationDialog = CustomDialog(requireContext())
        val confirmationDialogTitle = "Mensaje"
        //loadingDialog.showLoadingDialog()

//        viewModel.getListProducts { listProductsResponse: ListProductsResponse ->
//            if (listProductsResponse.status) {
//                val productList = listProductsResponse.productos
//                showProducts(productList)
//            } else {
//                Log.i("Product list:", listProductsResponse.message)
//            }
//        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showProducts(productList: List<Products>) {
        val adapter = ProductAdapter(productList)
        binding.rVProducts.layoutManager = LinearLayoutManager(requireContext())
        binding.rVProducts.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}