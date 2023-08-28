package com.example.foodie.ui.inventory

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodie.data.Products
import com.example.foodie.databinding.ActivityProductBinding
import com.example.foodie.ui.CustomDialog
import com.example.foodie.utils.Status

class ProductActivity : AppCompatActivity() {

    private val binding: ActivityProductBinding by lazy {
        ActivityProductBinding.inflate(layoutInflater)
    }

    private val loading by lazy {
        CustomDialog(this)
    }

    private val viewModel: InventoryProductsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val iBArrowBackButton = binding.iBArrowBackProduct
        iBArrowBackButton.setOnClickListener {
            finish()
        }
        callProductList()
    }

    private fun callProductList() {
        viewModel.getListProducts().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    // Si el estado del recurso es SUCCESS
                    Status.SUCCESS -> {
                        resource.data?.productos?.let { it ->
                            // Verificar si hay datos en el recurso y si hay productos en esos datos
                            // El bloque 'let' se ejecutará si ambos condiciones son verdaderas
                            loading.dismissLoadingDialog()
                            showProducts(it)
                        }?: run {
                            // Si no hay datos o no hay productos en los datos. El operador
                            // Elvis (?:) es un operador de Kotlin que se utiliza para proporcionar
                            // un valor predeterminado en caso de que la expresión a la izquierda
                            // sea nula.
                            loading.showLoadingDialog()
                        }
                    }
                    Status.ERROR -> {
                        // Si el estado del recurso es ERROR
                        loading.dismissLoadingDialog()
                    }
                    Status.LOADING -> {
                        // Si el estado del recurso es LOADING
                        loading.showLoadingDialog()
                    }
                }
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showProducts(productList: List<Products>) {
        val adapter = ProductAdapter(productList,
            onClickListener = { products -> onItemSelected(products)},
            onClickDelete = { position -> onDeleteItem(position) })
        binding.rVProducts.layoutManager = LinearLayoutManager(this)
        binding.rVProducts.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun onDeleteItem(position: Int) {
        Toast.makeText(this, position.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun onItemSelected(products: Products) {
        Toast.makeText(this, products.nomProducto, Toast.LENGTH_SHORT).show()
    }

}