package com.gaspol.expert.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PredictionResponse(

	@field:SerializedName("Year_hist")
	val year: List<String>,

	@field:SerializedName("Quantity_pred")
	val quantityPredict: List<Int>,

	@field:SerializedName("Quantity_hist")
	val quantity: List<Int>,

	@field:SerializedName("Year_pred")
	val yearPredict: List<String>
)
