package com.example.appdaugia.utils

import android.content.Context
import android.widget.EditText
import android.widget.Toast
import com.example.appdaugia.service.response.BaseResponse

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
    fun BaseResponse.getAllErrors(): String {
        val builder = StringBuilder()

        // Thêm message gốc nếu có
        message?.let {
            builder.append(it)
            builder.append("\n")
        }

        // Duyệt errors động
        errors?.forEach { (_, messages) ->
            messages.forEach { msg ->
                builder.append(msg)
                builder.append("\n")
            }
        }

        return builder.toString().trim()
    }
}