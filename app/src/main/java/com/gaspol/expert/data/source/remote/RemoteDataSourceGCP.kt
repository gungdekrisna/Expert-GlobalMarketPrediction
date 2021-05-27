package com.gaspol.expert.data.source.remote

import android.util.Log
import com.gaspol.expert.data.source.remote.network.ApiResponse
import com.gaspol.expert.data.source.remote.network.ApiService
import com.gaspol.expert.data.source.remote.response.PredictionResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import okhttp3.RequestBody

class RemoteDataSourceGCP private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSourceGCP? = null

        fun getInstance(service: ApiService): RemoteDataSourceGCP =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSourceGCP(service)
            }
    }

    fun getPredictions(requestBody: RequestBody): Flowable<ApiResponse<PredictionResponse>> {
        val resultData = PublishSubject.create<ApiResponse<PredictionResponse>>()

        //get data from remote api
        val client = apiService.getPredictions(requestBody)

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