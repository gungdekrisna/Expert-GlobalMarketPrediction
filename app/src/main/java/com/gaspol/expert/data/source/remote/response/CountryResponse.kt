package com.gaspol.expert.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CountryResponse (
    @field:SerializedName("Name")
    val name: String,

    @field:SerializedName("Code")
    val code: String
)