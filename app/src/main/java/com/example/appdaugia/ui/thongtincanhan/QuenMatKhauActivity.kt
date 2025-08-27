package com.example.appdaugia.ui.thongtincanhan

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appdaugia.MainActivity
import com.example.appdaugia.R
import com.example.appdaugia.service.request.ForgotRequest
import com.example.appdaugia.service.viewModel.AuthViewModel
import com.example.appdaugia.utils.LoadingDialog
import com.example.appdaugia.utils.SessionManager
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
    private lateinit var sessionManager: SessionManager
    private var viewModel = AuthViewModel()
    private lateinit var loadingDialog: LoadingDialog
    private lateinit var tv_sendok : TextView

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
        tv_sendok = findViewById(R.id.tv_sendok)

        loadingDialog = LoadingDialog(this)
        // Observe loading
        viewModel.loading.observe(this) { isLoading ->
            if (isLoading) loadingDialog.show()
            else loadingDialog.dismiss()
        }

        icBack.setOnClickListener {
            this.finish()
        }
        // vao man hinh an layout reset
        ln_reset_pass.visibility = View.GONE
        tv_sendok.visibility = View.GONE
        btnQuenMK.setOnClickListener {
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_email, "Input your email", this)) return@setOnClickListener
            //goi api quan mat khau
//            val request = ForgotRequest(
//                token = sessionManager.getToken(),
//                email = txt_email.text.toString()
//            )
//            viewModel.forgot(request)

            tv_sendok.visibility = View.VISIBLE
        }

        btnSendPass.setOnClickListener {
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_pass, "Input your password", this)) return@setOnClickListener
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_re_pass, "Input your confirm password", this)) return@setOnClickListener
            if (txt_pass.text != txt_re_pass.text){
                Toast.makeText(this, "Please confirm your password", Toast.LENGTH_SHORT).show()
                txt_re_pass.requestFocus()
            }
        }
        resForgot()
    }
    private fun resForgot(){
        viewModel.loginResult.observe(this) { result ->
            result.onSuccess { resp ->
                val status = resp.status
                val message = resp.message

                // khong cho sưa email
                txt_email.isEnabled = false
                // hien nhap mat khau
                ln_reset_pass.visibility = View.VISIBLE
                // an nut gui
                btnQuenMK.visibility = View.GONE

//                // Lưu session
//                resp.data?.let { sessionManager.saveUserSession(resp.data.id, resp.data.name, resp.token ) }
                // Chuyển sang màn hình chính

            }
            result.onFailure { e ->
                Toast.makeText(this, "onFailure: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}