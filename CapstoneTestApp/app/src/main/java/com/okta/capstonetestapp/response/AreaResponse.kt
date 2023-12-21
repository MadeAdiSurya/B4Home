package com.okta.capstonetestapp.response

import com.google.gson.annotations.SerializedName

data class AreaResponse (
    @field:SerializedName("LB")
    val lb: Int? = null,
    @field:SerializedName("LT")
    val lt: Int? = null,
)