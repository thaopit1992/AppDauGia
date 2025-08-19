package com.example.appdaugia.ui.thongtincanhan

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.appdaugia.R

class DangNhapActivity : AppCompatActivity() {
    private lateinit var btnDangKy: Button
    private lateinit var icBack: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dang_nhap)
        // Ẩn ActionBar (nếu có)
        supportActionBar?.hide()
        // Tìm View trong layout
        btnDangKy = findViewById(R.id.btnDangKy)
        icBack = findViewById(R.id.ic_back)

        btnDangKy.setOnClickListener {
            val intent = Intent(this, DangKyActivity::class.java)
            startActivity(intent)
        }

        icBack.setOnClickListener {
            this.finish()
        }
    }
}