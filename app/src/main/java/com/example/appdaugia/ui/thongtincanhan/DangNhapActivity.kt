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
import com.example.appdaugia.MainActivity
import com.example.appdaugia.R
import com.example.appdaugia.utils.SessionManager
import com.example.appdaugia.utils.Utils
import com.google.android.material.textfield.TextInputEditText

class DangNhapActivity : AppCompatActivity() {
    private lateinit var btnDangKy: Button
    private lateinit var icBack: ImageView
    private lateinit var tvForgot: TextView
    private lateinit var btnLogin: Button
    private lateinit var txt_email: TextInputEditText
    private lateinit var txt_pass: TextInputEditText
    private lateinit var sessionManager: SessionManager

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dang_nhap)
        // Ẩn ActionBar (nếu có)
        supportActionBar?.hide()
        sessionManager = SessionManager(this)
        // Tìm View trong layout
        btnDangKy = findViewById(R.id.btnDangKy)
        btnLogin = findViewById(R.id.btnLogin)
        icBack = findViewById(R.id.ic_back)
        tvForgot = findViewById(R.id.tvForgot)
        txt_email = findViewById(R.id.txt_email)
        txt_pass = findViewById(R.id.txt_pass)

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
            val username = txt_email.text.toString()
            val password = txt_pass.text.toString()

            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_email, "Input your email", this)) return@setOnClickListener
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_pass, "Input your pass word", this)) return@setOnClickListener

            // Giả định logic kiểm tra đăng nhập thành công
            if (username == "thaopx" && password == "123") {
                // Lưu session
                sessionManager.saveUserSession("u12345", username)
                // Chuyển sang màn hình chính
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đúng.", Toast.LENGTH_SHORT).show()
            }
        }

        // xu ly nut back ban phim = icBack
        // Đăng ký callback
        onBackPressedDispatcher.addCallback(this, callback)
    }
    // Biến callback phải được khai báo ở phạm vi class
    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            finish()
        }
    }


}