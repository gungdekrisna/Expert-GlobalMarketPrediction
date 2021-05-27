package com.gaspol.expert.data.source.remote.network

import com.gaspol.expert.data.source.remote.response.ListCountries
import com.gaspol.expert.data.source.remote.response.PredictionResponse
import io.reactivex.Flowable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("countries")
    fun getCountries(): Flowable<ListCountries>

    @POST("predict_api")
    fun getPredictions(@Body requestBody: RequestBody): Flowable<PredictionResponse>
}