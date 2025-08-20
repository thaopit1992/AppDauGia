package com.example.appdaugia.ui.thongtincanhan

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appdaugia.R
import com.example.appdaugia.utils.Utils
import com.google.android.material.textfield.TextInputEditText

class DoiMatKhauActivity : AppCompatActivity() {
    private lateinit var txt_old_pass : TextInputEditText
    private lateinit var icBack: ImageView
    private lateinit var txt_pass: TextInputEditText
    private lateinit var txt_re_pass: TextInputEditText
    private lateinit var btnSendChange : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doi_mat_khau)
        supportActionBar?.hide()

        txt_old_pass = findViewById(R.id.txt_old_pass)
        icBack = findViewById(R.id.ic_back)
        txt_pass = findViewById(R.id.txt_pass)
        txt_re_pass = findViewById(R.id.txt_re_pass)
        btnSendChange = findViewById(R.id.btnSendChange)

        icBack.setOnClickListener {
            this.finish()
        }
        btnSendChange.setOnClickListener {
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_old_pass, "Input your Current Password", this)) return@setOnClickListener
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_pass, "Input your Password", this)) return@setOnClickListener
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_re_pass, "Input your Confirm Password", this)) return@setOnClickListener

            if (txt_pass.text != txt_re_pass.text){
                Toast.makeText(this, "Please confirm your password", Toast.LENGTH_SHORT).show()
                txt_re_pass.requestFocus()
            }
        }
    }
}