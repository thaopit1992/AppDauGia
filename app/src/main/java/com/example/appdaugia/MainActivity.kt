package com.example.appdaugia

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.appdaugia.databinding.ActivityMainBinding
import com.example.appdaugia.ui.thongtincanhan.DangNhapActivity
import com.example.appdaugia.utils.SessionManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sessionManager: SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {
            val splashScreen = installSplashScreen()
            super.onCreate(savedInstanceState)
            // Giữ splash 3 giây
            splashScreen.setKeepOnScreenCondition { true }
            Handler(Looper.getMainLooper()).postDelayed({
                splashScreen.setKeepOnScreenCondition { false }
            }, 1000)
            // Inflate layout chính
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            supportActionBar?.hide()

        // Full màn hình
        supportActionBar?.hide()
        window.statusBarColor = Color.TRANSPARENT
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true
        setupNavigation()
    }

    override fun onResume() {
        super.onResume()
        // check login
        sessionManager = SessionManager(this)
        if (!sessionManager.isLoggedIn()) {
            // Nếu chưa đăng nhập, chuyển đến màn hình đăng nhập
            val intent = Intent(this, DangNhapActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
    private fun setupNavigation() {
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
//                R.id.navigation_dashboard,
                R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}