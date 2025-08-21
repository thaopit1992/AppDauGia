package com.example.appdaugia.ui.home

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appdaugia.R
import com.example.appdaugia.adapter.MyOrderAdapter
import com.example.appdaugia.adapter.MyProductAdapter
import com.example.appdaugia.data.ListItem
import com.example.appdaugia.data.Product

class DetailActivity : AppCompatActivity() {
    private lateinit var icBack: ImageView
    private lateinit var recycler_view: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        // Ẩn ActionBar (nếu có)
        supportActionBar?.hide()

        icBack = findViewById(R.id.ic_back)
        recycler_view = findViewById(R.id.recycler_view)
        // lay du lieu
        val item: ListItem? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("Item", ListItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("user_object") as? ListItem
        }
        if (item != null) {
            val total = item.total
            val orderId = item.id_order
        }

        icBack.setOnClickListener {
            this.finish()
        }

        // Tạo dữ liệu mẫu
        val items = listOf(
            Product("Áo Sơ Mi Boxy Karants Thêu 2 Mặt Túi Hộp Local Brand Streetwear Hot Trend - KR183",
                "100", "2","200","10", "220"),
            Product("Áo Thun Polo Nam Thêu Chữ U,Chất Liệu Thấm Hút Mồ Hôi,Cổ Phối Màu",
                "100", "2","200","10", "220"),
            Product("Áo Sơ Mi Boxy Karants ",
                "100", "2","200","10", "220"),
            Product("Áo Sơ Mi Boxy Karants Thêu 2 Mặt Túi Hộp ",
                "100", "2","200","10", "220"),

            // Thêm nhiều item khác tại đây
        )

        recycler_view.layoutManager = LinearLayoutManager(this)

        // Khởi tạo Adapter và thiết lập sự kiện click
        val adapter = MyProductAdapter(items) { item ->
            // Chuyển sang màn hình mới và truyền dữ liệu
//            val intent = Intent(requireContext(), DetailActivity::class.java).apply {
//                putExtra("Item", item)
//            }
//            startActivity(intent)
        }
        recycler_view.adapter = adapter


    }
}