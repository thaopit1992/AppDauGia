package com.example.appdaugia.ui.thongtincanhan

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appdaugia.R

class CodeQuenMatKhauActivity : AppCompatActivity() {
    private lateinit var etCode1: EditText
    private lateinit var etCode2: EditText
    private lateinit var etCode3: EditText
    private lateinit var etCode4: EditText
    private lateinit var btn_verify: Button
    private lateinit var icBack: ImageView
    private lateinit var tv_email: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_quen_mat_khau)
        // Ẩn ActionBar (nếu có)
        supportActionBar?.hide()
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true

        etCode1 = findViewById(R.id.et_code1)
        etCode2 = findViewById(R.id.et_code2)
        etCode3 = findViewById(R.id.et_code3)
        etCode4 = findViewById(R.id.et_code4)
        btn_verify = findViewById(R.id.btn_verify)
        icBack = findViewById(R.id.ic_back)
        tv_email = findViewById(R.id.tv_email)
        val email = intent.getStringExtra("email")
        tv_email.text = email
        setupOtpInputs()

        btn_verify.setOnClickListener {
            val code = "${etCode1.text}${etCode2.text}${etCode3.text}${etCode4.text}"
            if (code.length == 4) {
                Toast.makeText(this, "OTP: $code", Toast.LENGTH_SHORT).show()
                // TODO: gọi API verify code
                val intent = Intent(this, XacNhanQuenMKActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Vui lòng nhập đủ mã OTP", Toast.LENGTH_SHORT).show()
            }
        }

        icBack.setOnClickListener {
            this.finish()
        }

    }
    private fun setupOtpInputs() {
        moveNextOnInput(etCode1, etCode2)
        moveNextOnInput(etCode2, etCode3)
        moveNextOnInput(etCode3, etCode4)

        // khi nhấn xóa, quay lại ô trước
        moveBackOnDelete(etCode2, etCode1)
        moveBackOnDelete(etCode3, etCode2)
        moveBackOnDelete(etCode4, etCode3)
    }
    private fun moveNextOnInput(current: EditText, next: EditText) {
        current.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 1) {
                    next.requestFocus()
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun moveBackOnDelete(current: EditText, previous: EditText) {
        current.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                if (current.text.isEmpty()) {
                    previous.requestFocus()
                    previous.setSelection(previous.text.length)
                }
            }
            false
        }
    }
}