package com.example.appdaugia.ui.home

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appdaugia.R
import com.example.appdaugia.adapter.MyProductAdapter
import com.example.appdaugia.data.OrderData
import com.example.appdaugia.service.viewModel.OrderViewModel
import com.example.appdaugia.utils.DialogUtils
import com.example.appdaugia.utils.LoadingDialog
import com.example.appdaugia.utils.SessionManager

class DetailActivity : AppCompatActivity() {
    private lateinit var icBack: ImageView
    private lateinit var id_order: TextView
    private lateinit var time: TextView
    private lateinit var tv_status: TextView
    private lateinit var payment_status: TextView
    private lateinit var payment_sv: TextView
    private lateinit var total_net_price: TextView
    private lateinit var total_vat: TextView
    private lateinit var tolal_price: TextView
    private lateinit var recycler_view: RecyclerView
    private lateinit var id_order_tv: TextView
    private lateinit var tv_bank: TextView
    private lateinit var tv_acc: TextView
    private lateinit var tv_add: TextView
    private lateinit var tv_acc_usd: TextView
    private lateinit var tv_acc_eur: TextView

    private lateinit var sessionManager: SessionManager
    private val viewModel = OrderViewModel()
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        // Ẩn ActionBar (nếu có)
        supportActionBar?.hide()
        window.statusBarColor = Color.TRANSPARENT
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true

        sessionManager = SessionManager(this)
        loadingDialog = LoadingDialog(this)
        // Observe loading
        viewModel.loading.observe(this) { isLoading ->
            if (isLoading) loadingDialog.show()
            else loadingDialog.dismiss()
        }

        icBack = findViewById(R.id.ic_back)
        recycler_view = findViewById(R.id.recycler_view)
        id_order = findViewById(R.id.id_order)
        time = findViewById(R.id.time)
        tv_status = findViewById(R.id.status)
        payment_status = findViewById(R.id.payment_status)
        payment_sv = findViewById(R.id.payment_sv)
        total_net_price = findViewById(R.id.total_net_price)
        total_vat = findViewById(R.id.total_vat)
        tolal_price = findViewById(R.id.tolal_price)
        id_order_tv = findViewById(R.id.id_order_tv)
        tv_bank = findViewById(R.id.tv_bank)
        tv_acc = findViewById(R.id.tv_acc)
        tv_add = findViewById(R.id.tv_add)
        tv_acc_usd = findViewById(R.id.tv_acc_usd)
        tv_acc_eur = findViewById(R.id.tv_acc_eur)


        // lay du lieu
        val item: OrderData? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("Item", OrderData::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("Item") as? OrderData
        }

        if (item != null) {
            //goi api login
            viewModel.getDetail(context = this, token = sessionManager.getToken(), id_encode = item.id_encode)
        }

        icBack.setOnClickListener {
            this.finish()
        }

        recycler_view.layoutManager = LinearLayoutManager(this)

        resListOrder()
        resBank()
    }

    private fun resListOrder(){
        viewModel.resultDetail.observe(this) { result ->
            result.onSuccess { resp ->
                val status = resp.status
                if(status == 1) {
                    id_order.text = resp.data?.code
                    time.text = resp.data?.buy_date
                    tv_status.text = resp.data?.shipping_status.toString()
                    payment_status.text = resp.data?.payment_status
                    payment_sv.text = resp.data?.payment_service
                    total_net_price.text = resp.data?.total_price_net
                    total_vat.text = resp.data?.vat_price
                    tolal_price.text = resp.data?.total_price
                    id_order_tv.text = resp.data?.code


                    resp.data?.items?.let { list ->   // <- lấy items
                        val adapter = MyProductAdapter(list) { item ->
//                            val intent = Intent(this, DetailActivity::class.java).apply {
//                                putExtra("Item", item)
//                            }
//                            startActivity(intent)
                        }
                        recycler_view.adapter = adapter
                    }

                    viewModel.getBank(context = this, id = resp.data?.studio?.id_encode, token = sessionManager.getToken())

                } else  {
                    DialogUtils.showCustomDialog(
                        context = this,
                        message = resp.message,
                    ) {}
                }
            }
            result.onFailure { e ->
//                Toast.makeText(this, "Failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun resBank(){
        viewModel.resultBank.observe(this) { result ->
            result.onSuccess { resp ->
                val status = resp.status
                if(status == 1) {
                    // tìm item có key = "bank"
                    val bankItem = resp.data?.find { it.key == "bank" }

                    bankItem?.let {
                        tv_bank.setText(it.value?.bank_name)
                        tv_acc.setText(it.value?.bank_account)
                        tv_add.setText(it.value?.bank_address)
                        tv_acc_usd.setText(it.value?.bank_account_usd)
                        tv_acc_eur.setText(it.value?.bank_account_euro)
                    }
                } else  {
                    DialogUtils.showCustomDialog(
                        context = this,
                        message = resp.message.toString(),
                    ) {}
                }
            }
            result.onFailure { e ->
//                Toast.makeText(this, "Failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}