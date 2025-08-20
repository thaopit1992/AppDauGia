package com.example.appdaugia.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appdaugia.R
import com.example.appdaugia.adapter.MyOrderAdapter
import com.example.appdaugia.data.ListItem
import com.example.appdaugia.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var recycler_view: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate layout từ XML
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, 0, 0, 0)
            insets
        }

        recycler_view = root.findViewById(R.id.recycler_view)
    // Tạo dữ liệu mẫu
        val items = listOf(
            ListItem("1523269983.12", "2025-08-19", "2","1.779,41","Processing"),
            ListItem("1523269983.12", "2025-08-19", "2","1.779,41","Processing"),
            ListItem("1523269983.12", "2025-08-19", "2","1.779,41","Processing"),
            ListItem("1523269983.12", "2025-08-19", "2","1.779,41","Processing"),
            ListItem("1523269983.12", "2025-08-19", "2","1.779,41","Processing"),
            ListItem("1523269983.12", "2025-08-19", "2","1.779,41","Processing"),
            // Thêm nhiều item khác tại đây
        )

        recycler_view.layoutManager = LinearLayoutManager(requireContext())

        // Khởi tạo Adapter và thiết lập sự kiện click
        val adapter = MyOrderAdapter(items) { item ->
            // Chuyển sang màn hình mới và truyền dữ liệu
//            val intent = Intent(this, DetailActivity::class.java).apply {
//                putExtra("selected_item", item)
//            }
//            startActivity(intent)
        }
        recycler_view.adapter = adapter

        return root
    }
}