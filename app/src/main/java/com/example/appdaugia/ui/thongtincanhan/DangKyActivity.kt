package com.example.appdaugia.ui.thongtincanhan

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appdaugia.R
import androidx.core.net.toUri
import androidx.core.widget.NestedScrollView
import com.example.appdaugia.utils.Utils
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent

class DangKyActivity : AppCompatActivity() {
    private lateinit var btnCheck: ImageView
    private lateinit var txtusertt: EditText
    private lateinit var btnDangKy: Button
    private lateinit var txt_name : EditText
    private lateinit var txtfirst : EditText
    private lateinit var txt_company : EditText
    private lateinit var txt_vat_id : EditText
    private lateinit var txt_street : EditText
    private lateinit var txt_number : EditText
    private lateinit var txt_poscode : EditText
    private lateinit var txt_location : EditText
    private lateinit var txt_phone : EditText
    private lateinit var txt_email : EditText
    private lateinit var txt_country : EditText
    private lateinit var txt_pass : EditText
    private lateinit var txt_re_pass : EditText
    private lateinit var ln_recip : LinearLayout
    private lateinit var txt_fullname_recip : EditText
    private lateinit var txt_street_recip : EditText
    private lateinit var txt_house_recip : EditText
    private lateinit var txt_postcode_recip : EditText
    private lateinit var txt_location_recip : EditText
    private lateinit var txt_country_recip : EditText
    private lateinit var check_dif : CheckBox
    private lateinit var nestedScrollView: NestedScrollView
    private lateinit var checkboxDieuKhoan: CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dang_ky)
        nestedScrollView = findViewById(R.id.nestedScrollView)

        // Ẩn ActionBar (nếu có)
        supportActionBar?.hide()
        // Tìm View trong layout
        btnCheck = findViewById(R.id.btnCheck)
        txtusertt = findViewById(R.id.txtuser)
        btnDangKy = findViewById(R.id.btnDangKy)
        txt_name = findViewById(R.id.txt_name)
        txtfirst = findViewById(R.id.txtfirst)
        txt_company = findViewById(R.id.txt_company)
        txt_vat_id = findViewById(R.id.txt_vat_id)
        txt_street = findViewById(R.id.txt_street)
        txt_number = findViewById(R.id.txt_number)
        txt_poscode = findViewById(R.id.txt_poscode)
        txt_location = findViewById(R.id.txt_location)
        txt_phone = findViewById(R.id.txt_phone)
        txt_email = findViewById(R.id.txt_email)
        txt_country = findViewById(R.id.txt_country)
        txt_pass = findViewById(R.id.txt_pass)
        txt_re_pass = findViewById(R.id.txt_re_pass)
        ln_recip = findViewById(R.id.ln_recip)
        check_dif = findViewById(R.id.check_dif)

        txt_fullname_recip = findViewById(R.id.txt_fullname_recip)
        txt_street_recip = findViewById(R.id.txt_street_recip)
        txt_house_recip = findViewById(R.id.txt_house_recip)
        txt_postcode_recip = findViewById(R.id.txt_postcode_recip)
        txt_location_recip = findViewById(R.id.txt_location_recip)
        txt_country_recip = findViewById(R.id.txt_country_recip)
        checkboxDieuKhoan = findViewById(R.id.checkboxDieuKhoan)

        ln_recip.visibility = View.GONE
        check_dif.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                ln_recip.visibility = View.VISIBLE
            } else {
                ln_recip.visibility = View.GONE
            }
        }

        btnCheck.setOnClickListener {
            val url = "https://www.tiktok.com/@" + txtusertt.text
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = url.toUri()
            startActivity(intent)
        }

        btnDangKy.isEnabled = false
        checkboxDieuKhoan.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                btnDangKy.isEnabled = true
            } else {
                btnDangKy.isEnabled = false
            }
        }

        btnDangKy.setOnClickListener {
                // check param
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_name, "Input your name", this)) return@setOnClickListener
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txtfirst, "Input your first name", this)) return@setOnClickListener
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_street, "Input your street", this)) return@setOnClickListener
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_number, "Input your house number", this)) return@setOnClickListener
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_poscode, "Input your postal code", this)) return@setOnClickListener
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_location, "Input your location", this)) return@setOnClickListener
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_country, "Input your country", this)) return@setOnClickListener
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_phone, "Input your telephone", this)) return@setOnClickListener
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_email, "Input your email", this)) return@setOnClickListener
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txtusertt, "Input your tiktok username", this)) return@setOnClickListener
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_pass, "Input your password", this)) return@setOnClickListener
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_re_pass, "Input your confirm password", this)) return@setOnClickListener
            if(check_dif.isChecked){
                if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_fullname_recip, "Input your fullname recipient", this)) return@setOnClickListener
                if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_street_recip, "Input your street", this)) return@setOnClickListener
                if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_house_recip, "Input your house number", this)) return@setOnClickListener
                if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_postcode_recip, "Input your postal code", this)) return@setOnClickListener
                if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_location_recip, "Input your location", this)) return@setOnClickListener
                if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_country_recip, "Input your country", this)) return@setOnClickListener
            }
            if (txt_pass.text != txt_re_pass.text){
                Toast.makeText(this, "Please confirm your password", Toast.LENGTH_SHORT).show()
                txt_re_pass.requestFocus()
            }

            Toast.makeText(this, "Test pass", Toast.LENGTH_SHORT).show()
            // call api

        }
    }
}