package com.example.foodie.ui

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import com.example.foodie.R
import com.example.foodie.databinding.DialogConfirmationBinding
import com.example.foodie.databinding.DialogConfirmationNegationBinding
import com.example.foodie.ui.login.LoginActivity

class CustomDialog(private val context: Context) {

    private lateinit var bindingConfirmation: DialogConfirmationBinding
    private lateinit var bindingConfirmationNegationBinding: DialogConfirmationNegationBinding
    private lateinit var customDialog: AlertDialog

    private fun initializeViewBindingConfirmation(dialogView: View) {
        bindingConfirmation = DialogConfirmationBinding.bind(dialogView)
    }

    private fun initializeViewBindingConfirmationNegation(dialogView: View) {
        bindingConfirmationNegationBinding = DialogConfirmationNegationBinding.bind(dialogView)
    }

    fun showLoadingDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null)
        customDialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .setCancelable(false)
            .create()
        customDialog.show()
    }

    fun dismissLoadingDialog() {
        customDialog.dismiss()
    }

    fun showConfirmationDialog(
        title: String,
        message: String,
        positiveButtonListener: () -> Unit
    ) {
        val dialogView = LayoutInflater.from(context)
            .inflate(R.layout.dialog_confirmation, null)

        initializeViewBindingConfirmation(dialogView)

        customDialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        bindingConfirmation.tVTitleDialogConfirmation.text = title
        bindingConfirmation.tVMessageDialogConfirmation.text = message

        bindingConfirmation.buttonOkDialogConfirmation.setOnClickListener {
            positiveButtonListener.invoke()
            customDialog.dismiss()
        }
        customDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        customDialog.show()
    }

    fun showPositiveNegativeDialog(
        title: String,
        message: String,
        positiveButtonListener: () -> Unit,
        negativeButtonListener: () -> Unit
    ) {
        val dialogView = LayoutInflater.from(context)
            .inflate(R.layout.dialog_confirmation_negation, null)

        initializeViewBindingConfirmationNegation(dialogView)

        customDialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        bindingConfirmationNegationBinding.textViewTitle.text = title
        bindingConfirmationNegationBinding.textViewMessage.text = message

        bindingConfirmationNegationBinding.buttonPositive.setOnClickListener {
            positiveButtonListener.invoke()
            customDialog.dismiss()
        }

        bindingConfirmationNegationBinding.buttonNegative.setOnClickListener {
            negativeButtonListener.invoke()
            customDialog.dismiss()
        }
        customDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        customDialog.show()
    }
}