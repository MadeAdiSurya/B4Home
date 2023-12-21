package com.okta.capstonetestapp.ui.screen.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okta.capstonetestapp.data.ApiConfig
import com.okta.capstonetestapp.data.Repository
import com.okta.capstonetestapp.response.KprResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.HttpException

class KprFormViewModel : ViewModel() {
    private val _kprResponse = MutableStateFlow<KprResponse?>(null)
    val kprResponse: StateFlow<KprResponse?> = _kprResponse

    val isLoading = MutableStateFlow(false)
    fun kpr(
        inputHarga: String,
        inputSukuBunga: String,
        inputUangMuka: String,
        inputJangkaWaktu: String
    ){
        val jsonObject = JSONObject()
        jsonObject.put("harga_rumah", inputHarga.toInt())
        jsonObject.put("suku_bunga", inputSukuBunga.toFloat())
        jsonObject.put("uang_muka", inputUangMuka.toInt())
        jsonObject.put("jangka_waktu", inputJangkaWaktu.toInt())

        val requestBody = jsonObject.toString()
            .toRequestBody("application/json".toMediaTypeOrNull())

        viewModelScope.launch {
            isLoading.value = true
            try {
                val response = Repository.getInstance(ApiConfig.getApiService()).kpr(requestBody)
                _kprResponse.value = response
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