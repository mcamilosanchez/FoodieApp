package com.example.foodie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.foodie.data.SharedPreferencesHelper
import com.example.foodie.databinding.ActivityLoginBinding
import com.example.foodie.databinding.ActivityMainBinding
import com.example.foodie.ui.login.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //Create an instance of SharedPreferencesHelper
    private val sharedPreferencesHelper: SharedPreferencesHelper by lazy {
        SharedPreferencesHelper(this)
    }

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer)
                as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = binding.bottomNavigationView
        setupWithNavController(bottomNavigationView, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val textViewTitle = binding.tVTitleToolbar
            when (destination.id) {
                R.id.inventoryFragment -> textViewTitle.text =
                    getString(R.string.name_fragment_inventory)
                R.id.salesPurchaseFragment -> textViewTitle.text =
                    getString(R.string.name_fragment_sales_purchase)
                R.id.accountingFragment -> textViewTitle.text =
                    getString(R.string.name_fragment_accounting)
                R.id.clientsSuppliersFragment -> textViewTitle.text =
                    getString(R.string.name_fragment_clients_suppliers)
            }
        }

        val toolbar: Toolbar = binding.toolbarMainActivity
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout_toolbar_item -> {
                sharedPreferencesHelper.clearPreferences()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}