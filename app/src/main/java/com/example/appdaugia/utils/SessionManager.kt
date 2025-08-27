package com.example.appdaugia.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = prefs.edit()

    companion object {
        private const val PREF_NAME = "MyAppSession"
        private const val IS_LOGGED_IN = "isLoggedIn"
        private const val USER_ID = "userId"
        private const val USER_NAME = "userName"
        private const val TOKEN = "token"
    }

    // Hàm để lưu trạng thái đã đăng nhập và thông tin người dùng
    fun saveUserSession(userId: Long?, userName: String?, token: String) {
        editor.putBoolean(IS_LOGGED_IN, true)
        editor.putString(TOKEN, token)
        editor.putLong(USER_ID, userId ?:0)
        editor.putString(USER_NAME, userName)
        editor.apply()
    }

    // Hàm để lấy trạng thái đăng nhập
    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(IS_LOGGED_IN, false)
    }

    // Hàm để lấy ID người dùng
    fun getUserId(): String? {
        return prefs.getString(USER_ID, null)
    }

    // Hàm để lấy token người dùng
    fun getToken(): String? {
        return prefs.getString(TOKEN, null)
    }

    // Hàm để lấy tên người dùng
    fun getUserName(): String? {
        return prefs.getString(USER_NAME, null)
    }

    // Hàm để xóa session (đăng xuất)
    fun logout() {
        editor.clear()
        editor.apply()
    }
}