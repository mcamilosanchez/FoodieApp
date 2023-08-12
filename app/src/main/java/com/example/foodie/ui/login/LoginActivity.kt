package com.example.foodie.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import com.example.foodie.MainActivity
import com.example.foodie.data.LoginResponse
import com.example.foodie.data.SharedPreferencesHelper
import com.example.foodie.databinding.ActivityLoginBinding
import com.example.foodie.ui.CustomDialog

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val sharedPreferencesHelper: SharedPreferencesHelper by lazy {
        SharedPreferencesHelper(this)
    }

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (sharedPreferencesHelper.isLoggedIn()) {
            // Usuario ya ha iniciado sesión, redirigir a la actividad principal
            startActivity(Intent(this, MainActivity::class.java))
            finish() // Finalizar la actividad de inicio de sesión para evitar que el usuario regrese a ella con el botón de retroceso
            return // Salir del método onCreate()
        }

        val btnLogin = binding.btnLogin
        btnLogin.setOnClickListener {
            onButtonLogin(btnLogin)
        }
    }

    private fun onButtonLogin(btnLogin: Button) {
        val user = binding.editTextUserLogin.text.toString()
        val pass = binding.editTextPassLogin.text.toString()
        //Variables del AlertDialog
        val loadingDialog = CustomDialog(this)
        val confirmationDialog = CustomDialog(this)
        val confirmationDialogTitle = "Mensaje login:"
        var confirmationDialogMessage = ""

        btnLogin.isEnabled = false

        loadingDialog.showLoadingDialog()

        viewModel.getLogin(user, pass) { loginResponse : LoginResponse ->
            loadingDialog.dismissLoadingDialog()
            btnLogin.isEnabled = true
            if (loginResponse.status) {
                confirmationDialogMessage = loginResponse.datos.nomUsuario
                confirmationDialog.showConfirmationDialog(confirmationDialogTitle,
                    confirmationDialogMessage,
                    positiveButtonListener = {
                        Toast.makeText(this,
                            "Login: ${loginResponse.datos.nomUsuario}",
                            Toast.LENGTH_LONG).show()
                        if(binding.checkboxRememberUser.isChecked) {
                            sharedPreferencesHelper.username = loginResponse.datos.nomUsuario
                            sharedPreferencesHelper.password = pass//Pending change
                            sharedPreferencesHelper.rememberUser = true
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                )
            } else {
                confirmationDialogMessage = loginResponse.message
                confirmationDialog.showConfirmationDialog(confirmationDialogTitle,
                    confirmationDialogMessage,
                    positiveButtonListener = {
                        Toast.makeText(this,
                            "Login: ${loginResponse.message}",
                            Toast.LENGTH_LONG).show()
                    }
                )
            }
        }
    }
}