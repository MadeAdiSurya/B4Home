package com.okta.capstonetestapp.ui.screen.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okta.capstonetestapp.data.ApiConfig
import com.okta.capstonetestapp.response.AreaResponse
import com.okta.capstonetestapp.data.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.HttpException

class TipeFormViewModel : ViewModel() {
    private val _areaResponse = MutableStateFlow<AreaResponse?>(null)
    val areaResponse: StateFlow<AreaResponse?> get() = _areaResponse

    val isLoading = MutableStateFlow(false)
    fun countArea(
        inputHarga: Long,
        selectedYear: Int,
    ){
        val jsonObject = JSONObject()
        jsonObject.put("input_harga", inputHarga)
        jsonObject.put("tahun", selectedYear)

        val requestBody = jsonObject.toString()
            .toRequestBody("application/json".toMediaTypeOrNull())

        viewModelScope.launch {
            isLoading.value = true
            try {
                val response = Repository.getInstance(ApiConfig.getApiService()).area(requestBody)
                _areaResponse.value = response
            } catch (e: HttpException) {
                println("Server error: ${e.message}")
            } catch (e: Exception) {
                println("Error: ${e.message}")
            } finally {
                isLoading.value = false
            }
        }
    }
}

