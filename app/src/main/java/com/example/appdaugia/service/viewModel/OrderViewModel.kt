package com.example.appdaugia.service.viewModel

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

class OrderViewModel  : ViewModel() {
    private val repository = OrderRepository()

    private val _result = MutableLiveData<Result<OrderResponse>>()
    val result: LiveData<Result<OrderResponse>> = _result

    private val _resultDetail = MutableLiveData<Result<DetailOrderResponse>>()
    val resultDetail: LiveData<Result<DetailOrderResponse>> = _resultDetail

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun getListOrder(token: String?) {
        viewModelScope.launch {
            _loading.postValue(true) // 👉 Bật loading
            val result = repository.getListOrder(token)
            _result.postValue(result)
            _loading.postValue(false) // 👉 Tắt loading
        }
    }

    fun getDetail(token: String?, id_encode: Long?) {
        viewModelScope.launch {
            _loading.postValue(true) // 👉 Bật loading
            val result = repository.getDetail(token, id_encode)
            _resultDetail.postValue(result)
            _loading.postValue(false) // 👉 Tắt loading
        }
    }

}