package com.gaspol.expert.data.source.remote.network

import com.gaspol.expert.data.source.remote.response.ListCountries
import io.reactivex.Flowable
import retrofit2.http.GET

interface ApiService {
    @GET("countries")
    fun getCountries(): Flowable<ListCountries>
}