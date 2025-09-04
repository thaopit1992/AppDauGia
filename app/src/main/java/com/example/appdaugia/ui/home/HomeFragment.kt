package com.example.appdaugia.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appdaugia.MainActivity
import com.example.appdaugia.R
import com.example.appdaugia.adapter.MyOrderAdapter
import com.example.appdaugia.data.OrderData
import com.example.appdaugia.service.viewModel.AuthViewModel
import com.example.appdaugia.service.viewModel.OrderViewModel
import com.example.appdaugia.utils.AppStrings
import com.example.appdaugia.utils.DialogUtils
import com.example.appdaugia.utils.LoadingDialog
import com.example.appdaugia.utils.SessionManager
import java.util.Calendar

class HomeFragment : Fragment() {

    private lateinit var recycler_view: RecyclerView
    private lateinit var time: TextView
    private lateinit var tv_name: TextView
    private lateinit var tv_phone: TextView
    private lateinit var tv_tiktok_us: TextView
    private lateinit var tv_view: TextView
    private lateinit var tv_email: TextView

    private lateinit var sessionManager: SessionManager
    private val viewModel = OrderViewModel()

    private lateinit var loadingDialog: LoadingDialog


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
        time = root.findViewById(R.id.time)
        tv_name = root.findViewById(R.id.tv_name)
        tv_tiktok_us = root.findViewById(R.id.tv_tiktok_us)
        tv_phone = root.findViewById(R.id.tv_phone)
        tv_view = root.findViewById(R.id.tv_view)
        tv_email = root.findViewById(R.id.tv_email)

        sessionManager = SessionManager(requireContext())
        loadingDialog = LoadingDialog(requireContext())
        // Observe loading
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) loadingDialog.show()
            else loadingDialog.dismiss()
        }

        // chao hoi
        // Lấy đối tượng lịch
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val greeting: String = when (hour) {
            in 6..11 -> "Good morning!"
            in 12..17 -> "Good afternoon!"
            in 18..21 -> "Good evening!"
            else -> "Good night!"
        }
        time.text = greeting
        tv_name.text = sessionManager.getUserName()
        tv_tiktok_us.text = sessionManager.getTikTok()
        tv_email.text = sessionManager.getEmail()
        tv_phone.text = sessionManager.getPhone()

        //goi api login
        viewModel.getListOrder(context = requireContext(), token = sessionManager.getToken())

        recycler_view.layoutManager = LinearLayoutManager(requireContext())
        resListOrder()

        return root
    }

    private fun resListOrder(){
        viewModel.result.observe(viewLifecycleOwner) { result ->
            result.onSuccess { resp ->
                val status = resp.status
                if(status == 1) {

                    resp.data?.let { list ->
                        tv_view.text = list.size.toString()

                        val adapter = MyOrderAdapter(list) { item ->
                            val intent = Intent(requireContext(), DetailActivity::class.java).apply {
                                putExtra("Item", item)
                            }
                            startActivity(intent)
                        }
                        recycler_view.adapter = adapter
                    }
                } else  {
                    DialogUtils.showCustomDialog(
                        context = requireContext(),
                        message = resp.message,
                    ) {}
                }
            }
            result.onFailure { e ->
//                Toast.makeText(requireContext(), "Failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}