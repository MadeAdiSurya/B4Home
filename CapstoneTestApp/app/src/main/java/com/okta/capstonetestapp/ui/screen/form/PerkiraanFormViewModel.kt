package com.okta.capstonetestapp.ui.screen.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.okta.capstonetestapp.data.ApiConfig
import com.okta.capstonetestapp.response.PredictionResponse
import com.okta.capstonetestapp.data.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.HttpException

class PerkiraanFormViewModel : ViewModel() {
    private val _predictionResponse = MutableStateFlow<PredictionResponse?>(null)
    val predictionResponse: StateFlow<PredictionResponse?> = _predictionResponse

    val isLoading = MutableStateFlow(false)
    fun predict(
        inputLuasBangunan: String,
        inputLuasTanah: String,
        inputKamarTidur: String,
        inputKamarMandi: String,
        inputGarasi: Boolean,
        selectedYear: Int,
        user: FirebaseUser?
    ) {
        val jsonObject = JSONObject()
        jsonObject.put("user_email", user?.email)
        jsonObject.put("lb", inputLuasBangunan.toInt())
        jsonObject.put("lt", inputLuasTanah.toInt())
        jsonObject.put("kt", inputKamarTidur.toInt())
        jsonObject.put("km", inputKamarMandi.toInt())
        jsonObject.put("grs", if (inputGarasi) 1 else 0)
        jsonObject.put("tahun", selectedYear)

        val requestBody = jsonObject.toString()
            .toRequestBody("application/json".toMediaTypeOrNull())

        viewModelScope.launch {
            isLoading.value = true
            try {
                val response = Repository.getInstance(ApiConfig.getApiService()).prediction(requestBody)
                _predictionResponse.value = response
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
