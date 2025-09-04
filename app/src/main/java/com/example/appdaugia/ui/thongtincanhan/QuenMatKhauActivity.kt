package com.example.appdaugia.ui.thongtincanhan

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.appdaugia.R
import com.example.appdaugia.service.request.ForgotRequest
import com.example.appdaugia.service.viewModel.AuthViewModel
import com.example.appdaugia.utils.AppStrings
import com.example.appdaugia.utils.DialogUtils
import com.example.appdaugia.utils.LoadingDialog
import com.example.appdaugia.utils.SessionManager
import com.example.appdaugia.utils.Utils
import com.google.android.material.textfield.TextInputEditText

class QuenMatKhauActivity : AppCompatActivity() {
    private lateinit var txt_email : TextInputEditText
    private lateinit var btnQuenMK : Button
    private lateinit var icBack: ImageView
    private val viewModel = AuthViewModel()
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quen_mat_khau)
        supportActionBar?.hide()
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true

        txt_email = findViewById(R.id.txt_email)
        icBack = findViewById(R.id.ic_back)
        btnQuenMK = findViewById(R.id.btnQuenMK)

        loadingDialog = LoadingDialog(this)
        // Observe loading
        viewModel.loading.observe(this) { isLoading ->
            if (isLoading) loadingDialog.show()
            else loadingDialog.dismiss()
        }

        icBack.setOnClickListener {
            this.finish()
        }

        btnQuenMK.setOnClickListener {
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_email, "Input your email", this)) return@setOnClickListener
            //goi api quan mat khau
            val request = ForgotRequest(
                token = AppStrings.TOKEN_BASE,
                email = txt_email.text.toString()
            )
            viewModel.forgot(context = this, request=request)
        }

        resForgot()
    }
    private fun resForgot(){
        viewModel.baseResult.observe(this) { result ->
            result.onSuccess { resp ->
                val status = resp.status

                if(status == 1){
                    DialogUtils.showCustomDialog(
                        context = this,
                        message = resp.message.toString(),
                    ) {
                        // Xử lý khi bấm OK
                        finish()
                    }
                } else {
                    DialogUtils.showCustomDialog(
                        context = this,
                        message = resp.message.toString()
                    ) {}
                }
            }
            result.onFailure { e ->
                Toast.makeText(this, "onFailure: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.errorMessage.observe(this) { msg ->
            if (!msg.isNullOrEmpty()) {
                DialogUtils.showCustomDialog(
                    context = this,
                    message = msg,
                ) {
                    // Xử lý khi bấm OK
                }
            }
        }
    }
}