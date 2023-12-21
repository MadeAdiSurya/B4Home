package com.okta.capstonetestapp.response

import com.google.gson.annotations.SerializedName

data class PredictionResponse(
	@field:SerializedName("price_now")
	val priceNow: Long? = null,
	@field:SerializedName("price_prediction")
	val pricePrediction: Long? = null
)
