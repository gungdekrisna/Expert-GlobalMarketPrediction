package com.gaspol.expert.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PredictionResponse(

	@field:SerializedName("Year")
	val year: List<String>,

	@field:SerializedName("Quantity_predict")
	val quantityPredict: List<Int>,

	@field:SerializedName("Quantity")
	val quantity: List<Int>,

	@field:SerializedName("Year_predict")
	val yearPredict: List<String>
)
