package com.gaspol.expert.data.source.remote.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {
    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    fun provideApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://laravel-api-expert-77u2xu7nnq-et.a.run.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(provideOkHttpClient())
            .build()
        return retrofit.create(ApiService::class.java)
    }

    fun provideApiServiceGCP(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://predictor-sl372lyawq-et.a.run.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(provideOkHttpClient())
            .build()
        return retrofit.create(ApiService::class.java)
    }
}