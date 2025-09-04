package com.example.appdaugia.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.appdaugia.R

object DialogUtils {
    fun showCustomDialog(
        context: Context,
        message: String,
        positiveText: String = "OK",
        onPositiveClick: (() -> Unit)? = null
    ) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_custom, null)
        val tvMessage = dialogView.findViewById<TextView>(R.id.tvMessage)
        val btnOk = dialogView.findViewById<TextView>(R.id.btnOk)

        tvMessage.text = message
        btnOk.text = positiveText

        val dialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        btnOk.setOnClickListener {
            dialog.dismiss()
            onPositiveClick?.invoke()
        }

        dialog.show()
    }
}