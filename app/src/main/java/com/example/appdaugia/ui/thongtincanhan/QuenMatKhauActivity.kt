package com.example.appdaugia.ui.thongtincanhan

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appdaugia.R
import com.example.appdaugia.utils.Utils
import com.google.android.material.textfield.TextInputEditText

class QuenMatKhauActivity : AppCompatActivity() {
    private lateinit var txt_email : TextInputEditText
    private lateinit var btnQuenMK : Button
    private lateinit var icBack: ImageView
    private lateinit var ln_reset_pass: LinearLayout
    private lateinit var txt_pass: TextInputEditText
    private lateinit var txt_re_pass: TextInputEditText
    private lateinit var btnSendPass : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quen_mat_khau)
        supportActionBar?.hide()
        txt_email = findViewById(R.id.txt_email)
        icBack = findViewById(R.id.ic_back)
        btnQuenMK = findViewById(R.id.btnQuenMK)
        ln_reset_pass = findViewById(R.id.ln_reset_pass)
        txt_pass = findViewById(R.id.txt_pass)
        txt_re_pass = findViewById(R.id.txt_re_pass)
        btnSendPass = findViewById(R.id.btnSendPass)

        icBack.setOnClickListener {
            this.finish()
        }
        // vao man hinh an layout reset
        ln_reset_pass.visibility = View.GONE
        btnQuenMK.setOnClickListener {
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_email, "Input your email", this)) return@setOnClickListener
            // gui thanh cong
            // khong cho sưa email
            txt_email.isEnabled = false
            // hien nhap mat khau
            ln_reset_pass.visibility = View.VISIBLE
            // an nut gui
            btnQuenMK.visibility = View.GONE

        }

        btnSendPass.setOnClickListener {
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_pass, "Input your password", this)) return@setOnClickListener
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_re_pass, "Input your confirm password", this)) return@setOnClickListener
            if (txt_pass.text != txt_re_pass.text){
                Toast.makeText(this, "Please confirm your password", Toast.LENGTH_SHORT).show()
                txt_re_pass.requestFocus()
            }
        }
    }
}