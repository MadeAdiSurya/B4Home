package com.okta.capstonetestapp.data

import com.okta.capstonetestapp.response.AreaResponse
import com.okta.capstonetestapp.response.KprResponse
import com.okta.capstonetestapp.response.PredictionResponse
import retrofit2.Callback
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class Repository private constructor(
    private val apiService: ApiService
){

    suspend fun prediction(requestBody: RequestBody): PredictionResponse {
        val call = apiService.predict(requestBody)
        return suspendCoroutine { continuation ->
            call.enqueue(object : Callback<PredictionResponse> {
                override fun onResponse(call: Call<PredictionResponse>, response: Response<PredictionResponse>) {
                    if (response.isSuccessful) {
                        val predictionResponse = response.body()
                        if (predictionResponse != null) {
                            continuation.resume(predictionResponse)
                        } else {
                            continuation.resumeWithException(IllegalStateException("Response body is null"))
                        }
                    } else {
                        continuation.resumeWithException(HttpException(response))
                    }
                }

                override fun onFailure(call: Call<PredictionResponse>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

    suspend fun area(requestBody: RequestBody): AreaResponse {
        val call = apiService.countArea(requestBody)
        return suspendCoroutine { continuation ->
            call.enqueue(object : Callback<AreaResponse> {
                override fun onResponse(call: Call<AreaResponse>, response: Response<AreaResponse>) {
                    if (response.isSuccessful) {
                        val areaResponse = response.body()
                        if (areaResponse != null) {
                            continuation.resume(areaResponse)
                        } else {
                            continuation.resumeWithException(IllegalStateException("Response body is null"))
                        }
                    } else {
                        continuation.resumeWithException(HttpException(response))
                    }
                }

                override fun onFailure(call: Call<AreaResponse>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

    suspend fun kpr(requestBody: RequestBody): KprResponse {
        val call = apiService.countKpr(requestBody)
        return suspendCoroutine { continuation ->
            call.enqueue(object : Callback<KprResponse> {
                override fun onResponse(call: Call<KprResponse>, response: Response<KprResponse>) {
                    if (response.isSuccessful) {
                        val kprResponse = response.body()
                        if (kprResponse != null) {
                            continuation.resume(kprResponse)
                        } else {
                            continuation.resumeWithException(IllegalStateException("Response body is null"))
                        }
                    } else {
                        continuation.resumeWithException(HttpException(response))
                    }
                }

                override fun onFailure(call: Call<KprResponse>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

    companion object{
        @JvmStatic
        fun getInstance(
            apiService: ApiService
        ): Repository = Repository(apiService)
    }
}