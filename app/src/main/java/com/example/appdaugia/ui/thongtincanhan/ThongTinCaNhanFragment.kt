package com.example.appdaugia.ui.thongtincanhan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.widget.EditText
import android.widget.PopupWindow
import com.example.appdaugia.MainActivity
import com.example.appdaugia.R
import com.example.appdaugia.utils.SessionManager

class ThongTinCaNhanFragment : Fragment() {
    private lateinit var user: TextView
    private lateinit var txt_name: EditText
    private lateinit var id_name: EditText
    private lateinit var txt_email: EditText
    private lateinit var txt_phone: EditText
    private lateinit var txt_tiktok: EditText
    private lateinit var txt_add: EditText
    private lateinit var txt_number: EditText
    private lateinit var txt_posstcode: EditText
    private lateinit var txt_location: EditText
    private lateinit var txt_contry: EditText

    private lateinit var sessionManager: SessionManager


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate layout từ XML
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, 0, 0, 0)
            insets
        }
        val btnMore = root.findViewById<ImageButton>(R.id.btnMore)
        user = root.findViewById(R.id.user)
        txt_name = root.findViewById(R.id.txt_name)
        id_name = root.findViewById(R.id.id_name)
        txt_email = root.findViewById(R.id.txt_email)
        txt_phone = root.findViewById(R.id.txt_phone)
        txt_tiktok = root.findViewById(R.id.txt_tiktok)
        txt_add = root.findViewById(R.id.txt_add)
        txt_number = root.findViewById(R.id.txt_number)
        txt_posstcode = root.findViewById(R.id.txt_posstcode)
        txt_location = root.findViewById(R.id.txt_location)
        txt_contry = root.findViewById(R.id.txt_contry)

        // Tìm View trong layout
        user.setOnClickListener {
            val intent = Intent(requireContext(), DangNhapActivity::class.java)
            startActivity(intent)
        }
        btnMore.setOnClickListener {
            showCustomMenu(it)
        }

        // Khởi tạo SessionManager với Context của Fragment
        sessionManager = SessionManager(requireContext())

        return root
    }

    private fun showCustomMenu(anchorView: View) {
        val inflater = LayoutInflater.from(requireContext())
        val customMenuView = inflater.inflate(R.layout.custom_menu_layout, null)
        // Tạo PopupWindow
        val popupWindow = PopupWindow(
            customMenuView,
            // Sử dụng WRAP_CONTENT để menu tự động co giãn theo nội dung
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true // Cho phép đóng popup khi click ra ngoài
        )

        // Đặt tọa độ hiển thị
        popupWindow.showAsDropDown(anchorView, 0, 0, Gravity.END)
        customMenuView.findViewById<View>(R.id.tv_option_1).setOnClickListener {
            val intent = Intent(requireContext(), ThayDoiThongTinActivity::class.java)
            startActivity(intent)
            //sửa thì hien cac truong duoc sua
            popupWindow.dismiss()
        }
        customMenuView.findViewById<View>(R.id.tv_option_2).setOnClickListener {
            val intent = Intent(requireContext(), DoiMatKhauActivity::class.java)
            startActivity(intent)
            popupWindow.dismiss()
        }
        customMenuView.findViewById<View>(R.id.tv_option_3).setOnClickListener {
            sessionManager.logout()
            // Chuyển sang màn hình Đăng nhập (thường là trong Activity)
             val intent = Intent(activity, MainActivity::class.java)
             startActivity(intent)
             requireActivity().finish()
            popupWindow.dismiss()
        }
    }
}