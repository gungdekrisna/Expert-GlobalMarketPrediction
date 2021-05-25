package com.gaspol.expert.data.source.remote

import android.util.Log
import com.gaspol.expert.data.source.remote.network.ApiResponse
import com.gaspol.expert.data.source.remote.network.ApiService
import com.gaspol.expert.data.source.remote.response.CountryResponse
import com.gaspol.expert.data.source.remote.response.PredictionResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    fun getAllCountries(): Flowable<ApiResponse<List<CountryResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<CountryResponse>>>()

        //get data from remote api
        val client = apiService.getCountries()

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({ response ->
                val dataArray = response.results
                resultData.onNext(if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty)
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getPredictions(): Flowable<ApiResponse<PredictionResponse>> {
        val resultData = PublishSubject.create<ApiResponse<PredictionResponse>>()

        //get data from remote api
        val client = apiService.getPredictions()

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({ response ->
                val dataArray = response
                resultData.onNext(if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty)
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}