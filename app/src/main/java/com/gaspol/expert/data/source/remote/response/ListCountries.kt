package com.gaspol.expert.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListCountries (
    @field:SerializedName("results")
    val results: List<CountryResponse>
)