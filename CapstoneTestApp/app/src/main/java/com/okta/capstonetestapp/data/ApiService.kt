package com.okta.capstonetestapp.data

import com.okta.capstonetestapp.response.AreaResponse
import com.okta.capstonetestapp.response.KprResponse
import com.okta.capstonetestapp.response.PredictionResponse
import retrofit2.Call
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("predict")
    fun predict(
        @Body requestBody: RequestBody
    ): Call<PredictionResponse>

    @POST("housetype")
    fun countArea(
        @Body requestBody: RequestBody
    ): Call<AreaResponse>

    @POST("kpr")
    fun countKpr(
        @Body requestBody: RequestBody
    ): Call<KprResponse>
}