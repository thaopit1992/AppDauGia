package com.example.appdaugia.service.viewModel

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.example.appdaugia.data.OrderData
import com.example.appdaugia.service.response.ApiResponse
import com.example.appdaugia.service.repository.OrderRepository
import com.example.appdaugia.service.response.DetailOrderResponse
import com.example.appdaugia.service.response.OrderResponse
import com.example.appdaugia.service.response.PaymentResponse
import com.example.appdaugia.utils.DialogUtils
import com.example.appdaugia.utils.NetworkUtils

class OrderViewModel  : ViewModel() {
    private val repository = OrderRepository()

    private val _result = MutableLiveData<Result<OrderResponse>>()
    val result: LiveData<Result<OrderResponse>> = _result

    private val _resultDetail = MutableLiveData<Result<DetailOrderResponse>>()
    val resultDetail: LiveData<Result<DetailOrderResponse>> = _resultDetail

    private val _resultBank = MutableLiveData<Result<PaymentResponse>>()
    val resultBank: LiveData<Result<PaymentResponse>> = _resultBank

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun getListOrder(context: Context, token: String?) {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            DialogUtils.showCustomDialog(
                context = context,
                message = "No Internet",
            ) {}
            return
        }
        _loading.postValue(true)   // bắt đầu
        viewModelScope.launch {
            val result = repository.getListOrder(token)
            _result.postValue(result)
            _loading.postValue(false) // 👉 Tắt loading
        }
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun getDetail(context: Context, token: String?, id_encode: Long?) {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            DialogUtils.showCustomDialog(
                context = context,
                message = "No Internet",
            ) {}
            return
        }
        _loading.postValue(true)   // bắt đầu
        viewModelScope.launch {
            val result = repository.getDetail(token, id_encode)
            _resultDetail.postValue(result)
            _loading.postValue(false) // 👉 Tắt loading
        }
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun getBank(context: Context, id: Long?, token: String?) {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            DialogUtils.showCustomDialog(
                context = context,
                message = "No Internet",
            ) {}
            return
        }
        _loading.postValue(true)   // bắt đầu
        viewModelScope.launch {
            val result = repository.getBank(id, token)
            _resultBank.postValue(result)
            _loading.postValue(false) // 👉 Tắt loading
        }
    }

}