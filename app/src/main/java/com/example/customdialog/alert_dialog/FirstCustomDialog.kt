package com.example.customdialog.alert_dialog

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class FirstCustomDialog(private val activity: Activity) : MaterialAlertDialogBuilder(activity){

    override fun create(): AlertDialog {

        return super.create()
    }
}