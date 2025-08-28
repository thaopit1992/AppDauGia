package com.example.appdaugia.ui.thongtincanhan

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appdaugia.R
import com.example.appdaugia.utils.Utils

class XacNhanQuenMKActivity : AppCompatActivity() {
    private lateinit var txt_pass: EditText
    private lateinit var txt_re_pass: EditText
    private lateinit var icBack: ImageView
    private lateinit var btn_reset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xac_nhan_quen_mkactivity)
        // Ẩn ActionBar (nếu có)
        supportActionBar?.hide()
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true

        txt_pass = findViewById(R.id.txt_pass)
        txt_re_pass = findViewById(R.id.txt_re_pass)
        btn_reset = findViewById(R.id.btn_reset)
        icBack = findViewById(R.id.ic_back)

        icBack.setOnClickListener {
            this.finish()
        }

        btn_reset.setOnClickListener {
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_pass, "Input your password", this)) return@setOnClickListener
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_re_pass, "Input your confirm password", this)) return@setOnClickListener
            if (txt_pass.text != txt_re_pass.text){
                Toast.makeText(this, "Please confirm your password", Toast.LENGTH_SHORT).show()
                txt_re_pass.requestFocus()
            }
        }
    }
}