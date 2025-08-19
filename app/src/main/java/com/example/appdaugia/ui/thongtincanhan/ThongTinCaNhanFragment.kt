package com.example.appdaugia.ui.thongtincanhan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.appdaugia.R

class ThongTinCaNhanFragment : Fragment() {
    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate layout từ XML
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)

        // Tìm View trong layout
        textView = root.findViewById(R.id.text_notifications)
        textView.text = "fragment_notifications"
        textView.setOnClickListener {
            val intent = Intent(requireContext(), DangNhapActivity::class.java)
            startActivity(intent)
        }

        return root
    }
}