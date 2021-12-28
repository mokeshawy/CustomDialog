package com.example.customdialog.alert_dialog

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.WindowManager
import androidx.annotation.StyleRes
import androidx.appcompat.app.AlertDialog
import com.example.customdialog.R
import com.example.customdialog.databinding.LayoutCustomDialogBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class FirstCustomDialog(
    private val activity: Activity,
    @StyleRes private val style: Int = R.style.CustomDialogTheme
) : MaterialAlertDialogBuilder(activity, style) {

    private var title: String? = null
    private var message: String? = null
    private var image: Int? = null
    private var positiveText: String? = null
    private var negativeText: String? = null
    private var clickButton: (() -> Unit)? = null
    private var positiveButtonStyle: ButtonDialogStyle? = null
    private var negativeButtonStyle: ButtonDialogStyle? = null
    lateinit var binding: LayoutCustomDialogBinding
    override fun create(): AlertDialog {
        binding = LayoutCustomDialogBinding.inflate(activity.layoutInflater)
        setView(binding.root)
        val dialog = super.create()

        setLayoutParams(dialog)
        initializeTitleText(title!!)
        initializeMessage(message!!)
        initializeImage(image!!)
        handlePositiveButton(positiveText!!, dialog)
        handleNegativeButton(negativeText!!, dialog)

        return dialog
    }

    class Builder() {
        private var title: String? = null
        private var message: String? = null
        private var image: Int? = null
        private var positiveText: String? = null
        private var negativeText: String? = null
        private var clickButton: (() -> Unit)? = null
        private var positiveButtonStyle: ButtonDialogStyle? = null
        private var negativeButtonStyle: ButtonDialogStyle? = null

        fun setTitle(title: String): Builder {
            this.title = title
            return this
        }

        fun setMessage(message: String): Builder {
            this.message = message
            return this
        }

        fun setIcon(image: Int): Builder {
            this.image = image
            return this
        }

        fun positiveButton(
            positiveText: String,
            positiveButtonDialogStyle: ButtonDialogStyle? = null,
            clickButton: () -> Unit
        ) {
            this.positiveText = positiveText
            this.positiveButtonStyle = positiveButtonDialogStyle!!
            this.clickButton = clickButton
        }

        fun negativeButton(
            negativeText: String,
            negativeButtonDialogStyle: ButtonDialogStyle? = null,
            clickButton: () -> Unit
        ) {
            this.negativeText = negativeText
            this.negativeButtonStyle = negativeButtonDialogStyle!!
            this.clickButton = clickButton
        }

    }

    private fun setLayoutParams(dialog: Dialog) {
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(dialog.window?.attributes)
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog.window?.attributes = layoutParams
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun initializeTitleText(title: String) {
        title.let {
            binding.title.visibility = View.VISIBLE
            binding.title.text = it
        }
    }

    private fun initializeMessage(message: String) {
        message.let {
            binding.message.visibility = View.VISIBLE
            binding.message.text = it
        }
    }

    private fun initializeImage(image: Int) {
        image.let {
            binding.imageView.visibility = View.VISIBLE
            binding.imageView.setImageResource(it)
        }
    }

    private fun handlePositiveButton(positiveText: String, dialog: Dialog) {
        if (positiveText != null) {
            binding.positiveButton.visibility = View.VISIBLE
            binding.positiveButton.text = positiveText
            binding.positiveButton.setOnClickListener {
                clickButton?.invoke()
                dialog.dismiss()
            }
        }
    }

    private fun handleNegativeButton(negativeText: String, dialog: Dialog) {
        if (negativeText != null) {
            binding.negativeButton.visibility = View.VISIBLE
            binding.negativeButton.text = negativeText
            binding.negativeButton.setOnClickListener {
                clickButton?.invoke()
                dialog.dismiss()
            }
        }
    }
}