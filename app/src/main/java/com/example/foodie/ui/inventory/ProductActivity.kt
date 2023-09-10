package com.example.foodie.ui.inventory

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodie.R
import com.example.foodie.data.Products
import com.example.foodie.databinding.ActivityProductBinding
import com.example.foodie.ui.CustomDialog
import com.example.foodie.utils.Status

class ProductActivity : AppCompatActivity() {

    private val binding: ActivityProductBinding by lazy {
        ActivityProductBinding.inflate(layoutInflater)
    }

    private val customDialog by lazy {
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
                            customDialog.dismissLoadingDialog()
                            showProducts(it)
                        }?: run {
                            // Si no hay datos o no hay productos en los datos. El operador
                            // Elvis (?:) es un operador de Kotlin que se utiliza para proporcionar
                            // un valor predeterminado en caso de que la expresión a la izquierda
                            // sea nula.
                            customDialog.showLoadingDialog()
                        }
                    }
                    Status.ERROR -> {
                        // Si el estado del recurso es ERROR
                        customDialog.dismissLoadingDialog()
                    }
                    Status.LOADING -> {
                        // Si el estado del recurso es LOADING
                        customDialog.showLoadingDialog()
                    }
                }
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showProducts(productList: List<Products>) {
        val adapter = ProductAdapter(productList,
            onClickModProduct = { products -> onItemSelected(products)},
            onClickDeleteProduct = { position -> onDeleteItem(position) })
        binding.rVProducts.layoutManager = LinearLayoutManager(this)
        binding.rVProducts.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun onDeleteItem(position: Int) {
        Toast.makeText(this, (position+1).toString(), Toast.LENGTH_SHORT).show()
    }

    private fun onItemSelected(product: Products) {
        val formModProductDialogView = LayoutInflater.from(this).
        inflate(R.layout.dialog_form_mod_product, null)

        val formModProductDialog : AlertDialog = AlertDialog.Builder(this)
            .setView(formModProductDialogView)
            .setCancelable(true)
            .create()
        formModProductDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        formModProductDialog.show()

        //onItemSelectedChanged(product)
    }

    private fun onItemSelectedChanged(product: Products) {
        viewModel.modProduct(product).let { changeProductResponse ->
            when (changeProductResponse.status) {
                Status.SUCCESS -> {
                    customDialog.dismissLoadingDialog()
                    customDialog.showConfirmationDialog( "Message",
                        changeProductResponse.data?.message.toString(),
                        positiveButtonListener = {
                            Toast.makeText(this,
                                "Modification: ${changeProductResponse.data?.message.toString()}",
                                Toast.LENGTH_LONG).show()
                        })
                }
                Status.LOADING -> {
                    Toast.makeText(this, changeProductResponse.status.toString(), Toast.LENGTH_SHORT).show()
                }
                Status.ERROR -> {
                    Toast.makeText(this, "Error changedProductResponse", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}