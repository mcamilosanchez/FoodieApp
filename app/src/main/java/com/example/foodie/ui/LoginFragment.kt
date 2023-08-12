package com.example.foodie.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.foodie.databinding.FragmentLoginBinding
import com.example.foodie.ui.login.LoginViewModel


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    /*

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnLogin = binding.btnLogin
        binding.btnLogin.setOnClickListener{
            onButtonLogin(btnLogin)
        }
    }

    private fun onButtonLogin(btnLogin: Button) {

        val user = binding.editTextUserLogin.text.toString()
        val pass = binding.editTextPassLogin.text.toString()
        //Variables del AlertDialog
        val loadingDialog = CustomDialog(requireContext())
        val confirmationDialog = CustomDialog(requireContext())
        val confirmationDialogTitle = "Login"
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
                        Toast.makeText(activity,
                            "Login: ${loginResponse.datos.nomUsuario}",
                            Toast.LENGTH_LONG).show()
                    })
            } else {
                confirmationDialogMessage = loginResponse.message
                confirmationDialog.showConfirmationDialog(confirmationDialogTitle,
                    confirmationDialogMessage,
                    positiveButtonListener = {
                        Toast.makeText(activity,
                            "Login: ${loginResponse.message}",
                            Toast.LENGTH_LONG).show()
                    })
            }
        }
    } */
}