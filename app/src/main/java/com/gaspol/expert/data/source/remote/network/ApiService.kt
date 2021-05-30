package com.gaspol.expert.data.source.remote.network

import com.gaspol.expert.data.source.remote.response.ListCountries
import com.gaspol.expert.data.source.remote.response.PredictionResponse
import com.gaspol.expert.data.source.remote.response.ResponseCommodity
import io.reactivex.Flowable
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {
    @GET("countries")
    fun getCountries(): Flowable<ListCountries>

    @FormUrlEncoded
    @POST("search_commodity")
    suspend fun searchCommodity(@Field("search") search: String): ResponseCommodity

    @POST("predict_api")
    fun getPredictions(@Body requestBody: RequestBody): Flowable<PredictionResponse>
}