package com.example.foodie.ui.inventory

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodie.R
import com.example.foodie.data.ConvertData
import com.example.foodie.data.Products
import com.example.foodie.databinding.ViewholderProductsBinding

class ProductAdapter(private val productList: List<Products>,
                     private val onClickListener: (Products) -> Unit,
                     private val onClickDelete: (Int) -> Unit) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding: ViewholderProductsBinding = ViewholderProductsBinding.bind(itemView)

        val tvNameProduct = binding.tvNameProduct
        val tvDescProduct = binding.tvDescProduct
        val tvPriceProduct = binding.tvPriceProduct
        val imageProduct = binding.imageProduct
        val btnModProduct = binding.lCModProducts
        val btnDelProduct = binding.lCDelProducts
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_products, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.tvNameProduct.text = product.nomProducto
        holder.tvDescProduct.text = product.descProducto
        holder.tvPriceProduct.text = ConvertData().stringToPrice(product.precioProducto)

        Glide.with(holder.itemView)
            .load(product.imageProducto)
            .into(holder.imageProduct)

        holder.btnDelProduct.setOnClickListener { onClickDelete(position) }
        holder.btnModProduct.setOnClickListener { onClickDelete(position) }

    }

    override fun getItemCount(): Int {
        return productList.size
    }
}