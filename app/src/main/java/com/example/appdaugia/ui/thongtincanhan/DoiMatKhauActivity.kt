package com.example.appdaugia.ui.thongtincanhan

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appdaugia.R
import com.example.appdaugia.service.request.ChangePassRequest
import com.example.appdaugia.service.request.ForgotRequest
import com.example.appdaugia.service.viewModel.AuthViewModel
import com.example.appdaugia.utils.AppStrings
import com.example.appdaugia.utils.LoadingDialog
import com.example.appdaugia.utils.SessionManager
import com.example.appdaugia.utils.Utils
import com.google.android.material.textfield.TextInputEditText

class DoiMatKhauActivity : AppCompatActivity() {
    private lateinit var icBack: ImageView
    private lateinit var txt_pass: TextInputEditText
    private lateinit var txt_re_pass: TextInputEditText
    private lateinit var btnSendChange : Button
    private val viewModel = AuthViewModel()
    private lateinit var loadingDialog: LoadingDialog
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doi_mat_khau)
        supportActionBar?.hide()
        window.statusBarColor = Color.TRANSPARENT
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true

        icBack = findViewById(R.id.ic_back)
        txt_pass = findViewById(R.id.txt_pass)
        txt_re_pass = findViewById(R.id.txt_re_pass)
        btnSendChange = findViewById(R.id.btnSendChange)

        loadingDialog = LoadingDialog(this)
        sessionManager = SessionManager(this)

        // Observe loading
        viewModel.loading.observe(this) { isLoading ->
            if (isLoading) loadingDialog.show()
            else loadingDialog.dismiss()
        }

        icBack.setOnClickListener {
            this.finish()
        }
        btnSendChange.setOnClickListener {
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_pass, "Input your Password", this)) return@setOnClickListener
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_re_pass, "Input your Confirm Password", this)) return@setOnClickListener

            if (!txt_pass.text.toString().trim().equals(txt_re_pass.text.toString().trim(), ignoreCase = false)) {
                Toast.makeText(this, "Please confirm your password", Toast.LENGTH_SHORT).show()
                txt_re_pass.requestFocus()
                return@setOnClickListener
            }

            val request = ChangePassRequest(
                token = sessionManager.getToken(),
                password = txt_pass.text.toString(),
                password_confirmation = txt_re_pass.text.toString(),
            )
            viewModel.chnagePass(request)
        }
        resChangePass()

    }

    private fun resChangePass(){
        viewModel.baseResult.observe(this) { result ->
            result.onSuccess { resp ->
                val status = resp.status

                if(status == 1){
                    // 👉 Đăng ký thành công
                    sessionManager.updateToken(resp.token)
                    AlertDialog.Builder(this)
                        .setMessage(resp.message)
                        .setPositiveButton("OK") { dialog, _ ->
                            dialog.dismiss()
                            finish()
                        }
                        .show()
                }
            }
            result.onFailure { e ->
                Toast.makeText(this, "onFailure: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}