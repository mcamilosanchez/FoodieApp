package com.example.foodie.ui.inventory

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.foodie.MainActivity
import com.example.foodie.R
import com.example.foodie.databinding.FragmentInventoryBinding

class InventoryFragment : Fragment() {

    private lateinit var binding: FragmentInventoryBinding
    private val viewModel: InventoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentInventoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lCProducts.setOnClickListener {
            //findNavController().navigate(R.id.action_inventoryFragment_to_inventoryProductsFragment)
            startActivity(Intent(requireContext(), ProductActivity::class.java))
        }

        binding.lCModDelProducts.setOnClickListener {
            Toast.makeText(activity, "click btnModDelProducts", Toast.LENGTH_SHORT).show()
        }

        binding.lCStockPrima.setOnClickListener {
            Toast.makeText(activity, "click btnStockPrima", Toast.LENGTH_SHORT).show()
        }

        binding.lCStockFinished.setOnClickListener {
            Toast.makeText(activity, "click btnStockFinished", Toast.LENGTH_SHORT).show()
        }

    }


}