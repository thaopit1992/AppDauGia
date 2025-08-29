package com.example.appdaugia.ui.thongtincanhan

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.appdaugia.MainActivity
import com.example.appdaugia.R
import com.example.appdaugia.service.viewModel.AuthViewModel
import com.example.appdaugia.utils.AppStrings
import com.example.appdaugia.utils.LoadingDialog
import com.example.appdaugia.utils.SessionManager
import com.example.appdaugia.utils.Utils
import com.google.android.material.textfield.TextInputEditText

class DangNhapActivity : AppCompatActivity() {
    private lateinit var btnDangKy: TextView
    private lateinit var icBack: ImageView
    private lateinit var tvForgot: TextView
    private lateinit var btnLogin: Button
    private lateinit var txt_email: TextInputEditText
    private lateinit var txt_pass: TextInputEditText

    private lateinit var sessionManager: SessionManager
    private var viewModel = AuthViewModel()
    private lateinit var loadingDialog: LoadingDialog  // custom dialog ở bước trước

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dang_nhap)
        // Ẩn ActionBar (nếu có)
        supportActionBar?.hide()
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true

        sessionManager = SessionManager(this)
        // Tìm View trong layout
        btnDangKy = findViewById(R.id.btnDangKy)
        btnLogin = findViewById(R.id.btnLogin)
        icBack = findViewById(R.id.ic_back)
        tvForgot = findViewById(R.id.tvForgot)
        txt_email = findViewById(R.id.txt_email)
        txt_pass = findViewById(R.id.txt_pass)

        loadingDialog = LoadingDialog(this)
        // Observe loading
        viewModel.loading.observe(this) { isLoading ->
            if (isLoading) loadingDialog.show()
            else loadingDialog.dismiss()
        }

        tvForgot.setOnClickListener {
            val intent = Intent(this, QuenMatKhauActivity::class.java)
            startActivity(intent)
        }

        btnDangKy.setOnClickListener {
            val intent = Intent(this, DangKyActivity::class.java)
            startActivity(intent)
        }

        icBack.setOnClickListener {
            this.finish()
        }

        btnLogin.setOnClickListener {
            val username = txt_email.text.toString().trim()
            val password = txt_pass.text.toString().trim()

            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_email, "Input your email", this)) return@setOnClickListener
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_pass, "Input your pass word", this)) return@setOnClickListener

            //goi api login
            viewModel.login(AppStrings.TOKEN_BASE,username, password)
        }

        // xu ly nut back ban phim = icBack
        // Đăng ký callback
        onBackPressedDispatcher.addCallback(this, callback)

        resLogin()
    }
    // Biến callback phải được khai báo ở phạm vi class
    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            finish()
        }
    }

    private fun resLogin(){
        viewModel.loginResult.observe(this) { result ->
            result.onSuccess { resp ->
                val status = resp.status
                val message = resp.message
                // Lưu session
                resp.data?.let { sessionManager.saveUserSession(resp.data.id, resp.data.name, resp.token ,
                    resp.data.email, resp.data.tiktok_username, resp.data.phone
                    ) }
                // Chuyển sang màn hình chính
                startActivity(Intent(this, MainActivity::class.java))
                finish()
                Toast.makeText(this, "Login success! Token: ${resp.token}", Toast.LENGTH_SHORT).show()
            }
            result.onFailure { e ->
                Toast.makeText(this, "Login failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }


}