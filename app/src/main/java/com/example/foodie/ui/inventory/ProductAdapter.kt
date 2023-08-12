package com.example.foodie.ui.inventory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodie.R
import com.example.foodie.data.ConvertData
import com.example.foodie.data.Products
import com.example.foodie.databinding.ViewholderProductsBinding

class ProductAdapter(private val productList: List<Products>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding: ViewholderProductsBinding = ViewholderProductsBinding.bind(itemView)

        val tvNameProduct = binding.tvNameProduct
        val tvDescProduct = binding.tvDescProduct
        val tvPriceProduct = binding.tvPriceProduct
        val imageProduct = binding.imageProduct


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
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}