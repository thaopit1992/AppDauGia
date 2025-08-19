package com.example.appdaugia.ui.thongtincanhan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.appdaugia.R

class ThongTinCaNhanFragment : Fragment() {
    private lateinit var user: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate layout từ XML
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        user = root.findViewById(R.id.user)
        // Tìm View trong layout
        user.setOnClickListener {
            val intent = Intent(requireContext(), DangNhapActivity::class.java)
            startActivity(intent)
        }



        return root
    }
}