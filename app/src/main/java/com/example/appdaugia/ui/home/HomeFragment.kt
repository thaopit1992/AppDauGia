package com.example.appdaugia.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.appdaugia.R
import com.example.appdaugia.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate layout từ XML
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        // Tìm View trong layout
        textView = root.findViewById(R.id.text_home)
        textView.text = "fragment_home"

        return root
    }
}