package com.example.appdaugia.utils

import android.content.Context
import android.widget.EditText
import android.widget.Toast

class Utils {
    object ValidationUtils {
        fun checkEditTextNotEmpty(et: EditText, message: String, context: Context): Boolean {
            if (et.text.toString().trim().isEmpty()) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                et.requestFocus()
                return false
            }
            return true
        }
    }
}