package com.okta.capstonetestapp.response

import com.google.gson.annotations.SerializedName

data class KprResponse (
    @field:SerializedName("debts")
    val debts: Long? = null,
    @field:SerializedName("monthly_installment")
    val monthlyInstallment: Long? = null,
    @field:SerializedName("total")
    val total: Long? = null,
)