package com.gaspol.expert.data.source.remote.network

import com.gaspol.expert.data.source.remote.response.ListCountries
import com.gaspol.expert.data.source.remote.response.PredictionResponse
import io.reactivex.Flowable
import retrofit2.http.GET

interface ApiService {
    @GET("countries")
    fun getCountries(): Flowable<ListCountries>

    @GET("prediction")
    fun getPredictions(): Flowable<PredictionResponse>
}