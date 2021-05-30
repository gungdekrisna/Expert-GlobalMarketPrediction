package com.gaspol.expert.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseCommodity(

	@field:SerializedName("result")
	val result: List<ResultCommodityItem?>? = null
)

data class ResultCommodityItem(

	@field:SerializedName("updated_at")
	val updatedAt: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
