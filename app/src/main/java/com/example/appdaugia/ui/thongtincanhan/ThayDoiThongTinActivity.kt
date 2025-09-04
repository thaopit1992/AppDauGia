package com.example.appdaugia.ui.thongtincanhan

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appdaugia.R
import com.example.appdaugia.adapter.CountryAdapter
import com.example.appdaugia.data.CountryData
import com.example.appdaugia.service.request.RegisterRequest
import com.example.appdaugia.service.viewModel.AuthViewModel
import com.example.appdaugia.utils.AppStrings
import com.example.appdaugia.utils.DialogUtils
import com.example.appdaugia.utils.LoadingDialog
import com.example.appdaugia.utils.SessionManager
import com.example.appdaugia.utils.Utils
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ThayDoiThongTinActivity : AppCompatActivity() {
    private lateinit var icBack: ImageView
    private lateinit var btnCheck: ImageView
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
    private lateinit var ln_recip : LinearLayout
    private lateinit var txt_fullname_recip : EditText
    private lateinit var txt_street_recip : EditText
    private lateinit var txt_house_recip : EditText
    private lateinit var txt_postcode_recip : EditText
    private lateinit var txt_location_recip : EditText
    private lateinit var txt_country_recip : EditText
    private lateinit var check_dif : CheckBox
    private lateinit var nestedScrollView: NestedScrollView
    private lateinit var txt_tiktok: EditText
    private lateinit var lo_country : TextInputLayout
    private lateinit var lo_country_rep : TextInputLayout
    private lateinit var sessionManager: SessionManager
    private var viewModel = AuthViewModel()
    private lateinit var loadingDialog: LoadingDialog
    var countries: List<CountryData> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thay_doi_thong_tin)
        nestedScrollView = findViewById(R.id.nestedScrollView)
        supportActionBar?.hide()
        window.statusBarColor = Color.TRANSPARENT
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true

        icBack = findViewById(R.id.ic_back)
        btnCheck = findViewById(R.id.btnCheck)
        txt_tiktok = findViewById(R.id.txt_tiktok)
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
        ln_recip = findViewById(R.id.ln_recip)
        check_dif = findViewById(R.id.check_dif)

        txt_fullname_recip = findViewById(R.id.txt_fullname_recip)
        txt_street_recip = findViewById(R.id.txt_street_recip)
        txt_house_recip = findViewById(R.id.txt_house_recip)
        txt_postcode_recip = findViewById(R.id.txt_postcode_recip)
        txt_location_recip = findViewById(R.id.txt_location_recip)
        txt_country_recip = findViewById(R.id.txt_country_recip)
        txt_tiktok = findViewById(R.id.txt_tiktok)
        lo_country = findViewById(R.id.lo_country)
        lo_country_rep = findViewById(R.id.lo_country_rep)

        sessionManager = SessionManager(this)

        loadingDialog = LoadingDialog(this)
        viewModel.loading.observe(this) { isLoading ->
            if (isLoading) loadingDialog.show()
            else loadingDialog.dismiss()
        }

        //goi api getuser
        viewModel.getUser(context = this, token = sessionManager.getToken())
        resGetUser()

        viewModel.getListCountry(context = this, token = AppStrings.TOKEN_BASE)
        resGetListCountry()
        lo_country.setEndIconOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this, R.style.AppBottomSheetDialogTheme)
            val view = layoutInflater.inflate(R.layout.bottom_sheet_countries, null)
            bottomSheetDialog.setContentView(view)

            // RecyclerView + Search
            val rv = view.findViewById<RecyclerView>(R.id.rvCountries)
            val etSearch = view.findViewById<TextInputEditText>(R.id.etSearchCountry)

            val adapter = CountryAdapter(countries) { selected ->
                txt_country.setText(selected.name)   // Fill vào EditText chính
                bottomSheetDialog.dismiss()
            }

            rv.layoutManager = LinearLayoutManager(this)
            rv.adapter = adapter

            // Lắng nghe search
            etSearch.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    adapter.filter.filter(s.toString())
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            // Xử lý khi show
            bottomSheetDialog.setOnShowListener { dialog ->
                val d = dialog as BottomSheetDialog
                val bottomSheet = d.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
                bottomSheet?.let {
                    // Xoá nền mặc định để lộ drawable bo góc
                    it.setBackgroundResource(android.R.color.transparent)

                    // Chiếm 90% màn hình
                    val layoutParams = it.layoutParams
                    layoutParams.height = (resources.displayMetrics.heightPixels * 0.9f).toInt()
                    it.layoutParams = layoutParams

                    val behavior = BottomSheetBehavior.from(it)
                    behavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
            bottomSheetDialog.show()
        }

        lo_country_rep.setEndIconOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this, R.style.AppBottomSheetDialogTheme)
            val view = layoutInflater.inflate(R.layout.bottom_sheet_countries, null)
            bottomSheetDialog.setContentView(view)

            // RecyclerView + Search
            val rv = view.findViewById<RecyclerView>(R.id.rvCountries)
            val etSearch = view.findViewById<TextInputEditText>(R.id.etSearchCountry)

            val adapter = CountryAdapter(countries) { selected ->
                txt_country_recip.setText(selected.name)   // Fill vào EditText chính
                bottomSheetDialog.dismiss()
            }

            rv.layoutManager = LinearLayoutManager(this)
            rv.adapter = adapter

            // Lắng nghe search
            etSearch.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    adapter.filter.filter(s.toString())
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            // Xử lý khi show
            bottomSheetDialog.setOnShowListener { dialog ->
                val d = dialog as BottomSheetDialog
                val bottomSheet = d.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
                bottomSheet?.let {
                    // Xoá nền mặc định để lộ drawable bo góc
                    it.setBackgroundResource(android.R.color.transparent)

                    // Chiếm 90% màn hình
                    val layoutParams = it.layoutParams
                    layoutParams.height = (resources.displayMetrics.heightPixels * 0.9f).toInt()
                    it.layoutParams = layoutParams

                    val behavior = BottomSheetBehavior.from(it)
                    behavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
            bottomSheetDialog.show()
        }

        ln_recip.visibility = View.GONE
        check_dif.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                ln_recip.visibility = View.VISIBLE
            } else {
                ln_recip.visibility = View.GONE
            }
        }
        icBack.setOnClickListener {
            this.finish()
        }

        btnCheck.setOnClickListener {
            val url = "https://www.tiktok.com/@" + txt_tiktok.text
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = url.toUri()
            startActivity(intent)
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
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_tiktok, "Input your tiktok username", this)) return@setOnClickListener

            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_fullname_recip, "Input your fullname shipping", this)) return@setOnClickListener
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_street_recip, "Input your street shipping", this)) return@setOnClickListener
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_house_recip, "Input your house number shipping", this)) return@setOnClickListener
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_postcode_recip, "Input your postal code shipping", this)) return@setOnClickListener
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_location_recip, "Input your location shipping", this)) return@setOnClickListener
            if (!Utils.ValidationUtils.checkEditTextNotEmpty(txt_country_recip, "Input your country shipping", this)) return@setOnClickListener

            // call api
            val request = RegisterRequest(
                token = sessionManager.getToken(),
                name = txt_name.text.toString().trim(),
                first_name = txt_name.text.toString().trim(),
                company = txt_company.text.toString().trim(),
                vat_id = txt_vat_id.text.toString().trim(),

                address = txt_street.text.toString().trim(),
                house_number = txt_number.text.toString().trim(),
                postal_code = txt_poscode.text.toString().trim(),
                location = txt_location.text.toString().trim(),
                country = txt_country.text.toString().trim(),

                shipping_fullname = txt_fullname_recip.text.toString().trim(),
                shipping_house_number = txt_house_recip.text.toString().trim(),
                shipping_address = txt_street_recip.text.toString().trim(),
                shipping_postal_code = txt_postcode_recip.text.toString().trim(),
                shipping_location = txt_location_recip.text.toString().trim(),
                shipping_country = txt_country_recip.text.toString().trim(),

                phone = txt_phone.text.toString().trim(),
                email = txt_email.text.toString().trim(),

                tiktok_username = txt_tiktok.text.toString().trim(),
                check_terms = null
            )
            viewModel.editeUser(context = this, request = request)
        }

        resEditUser()
    }

    private fun resGetUser(){
        viewModel.loginResult.observe(this) { result ->
            result.onSuccess { resp ->
                val status = resp.status
                val message = resp.message
                //  Read data
                if(status == 1){
                    resp.data?.let {
                        txt_name.setText(resp.data.name ?: "")
                        txtfirst.setText(resp.data.first_name ?: "")
                        txt_company.setText(resp.data.company ?:"")
                        txt_vat_id.setText(resp.data.vat_id ?:"")
                        txt_street.setText(resp.data.address ?:"")
                        txt_number.setText(resp.data.house_number?:"")
                        txt_poscode.setText(resp.data.postal_code?:"")
                        txt_location.setText(resp.data.location?:"")
                        txt_country.setText(resp.data.country?:"")

                        txt_fullname_recip.setText(resp.data.shipping_fullname?:"")
                        txt_street_recip.setText(resp.data.shipping_address?:"")
                        txt_house_recip.setText(resp.data.shipping_house_number?:"")
                        txt_postcode_recip.setText(resp.data.shipping_postal_code?:"")
                        txt_location_recip.setText(resp.data.shipping_location?:"")
                        txt_country_recip.setText(resp.data.shipping_country?:"")

                        txt_phone.setText(resp.data.phone?:"")
                        txt_email.setText(resp.data.email?:"")
                        txt_tiktok.setText(resp.data.tiktok_username?:"")
                    }
                } else {
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }
            }
            result.onFailure { e ->
                Toast.makeText(this, "Get Info failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.errorMessage.observe(this) { msg ->
            if (!msg.isNullOrEmpty()) {
                DialogUtils.showCustomDialog(
                    context = this,
                    message = msg,
                ) {
                    // Xử lý khi bấm OK
                }
            }
        }
    }

    private fun resEditUser(){
        viewModel.editUserResult.observe(this) { result ->
            result.onSuccess { resp ->
                val status = resp.status
                val message = resp.message
                //  Read data
                if(status == 1){
                    DialogUtils.showCustomDialog(
                        context = this,
                        message = resp.message.toString(),
                    ) {
                        // Xử lý khi bấm OK
                    }

                    resp.data?.let {
                        txt_name.setText(resp.data.name ?: "")
                        txtfirst.setText(resp.data.first_name ?: "")
                        txt_company.setText(resp.data.company ?:"")
                        txt_vat_id.setText(resp.data.vat_id ?:"")
                        txt_street.setText(resp.data.address ?:"")
                        txt_number.setText(resp.data.house_number?:"")
                        txt_poscode.setText(resp.data.postal_code?:"")
                        txt_location.setText(resp.data.location?:"")
                        txt_country.setText(resp.data.country?:"")

                        txt_fullname_recip.setText(resp.data.shipping_fullname?:"")
                        txt_street_recip.setText(resp.data.shipping_address?:"")
                        txt_house_recip.setText(resp.data.shipping_house_number?:"")
                        txt_postcode_recip.setText(resp.data.shipping_postal_code?:"")
                        txt_location_recip.setText(resp.data.shipping_location?:"")
                        txt_country_recip.setText(resp.data.shipping_country?:"")

                        txt_phone.setText(resp.data.phone?:"")
                        txt_email.setText(resp.data.email?:"")
                        txt_tiktok.setText(resp.data.tiktok_username?:"")
                    }
                } else {
                    DialogUtils.showCustomDialog(
                        context = this,
                        message = resp.message.toString()
                    ) {}
                }
            }
            result.onFailure { e ->
                DialogUtils.showCustomDialog(
                    context = this,
                    message = e.message.toString()
                ) {}
            }
        }
        viewModel.errorMessage.observe(this) { msg ->
            if (!msg.isNullOrEmpty()) {
                DialogUtils.showCustomDialog(
                    context = this,
                    message = msg,
                ) {
                    // Xử lý khi bấm OK
                }
            }
        }
    }

    private fun resGetListCountry(){
        viewModel.getListCountryResult.observe(this) { result ->
            result.onSuccess { response ->
                if (response.status == 1) {
                    countries = response.data ?: emptyList()
                } else {
                    DialogUtils.showCustomDialog(
                        context = this,
                        message = response.message.toString()
                    ) {}
                }
            }
        }
        viewModel.errorMessage.observe(this) { msg ->
            if (!msg.isNullOrEmpty()) {
                DialogUtils.showCustomDialog(
                    context = this,
                    message = msg,
                ) {
                    // Xử lý khi bấm OK
                    finish()
                }
            }
        }
    }
}