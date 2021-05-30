package com.gaspol.expert.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.gaspol.expert.data.source.local.LocalDataSource
import com.gaspol.expert.data.source.local.entity.RecentSearchEntity
import com.gaspol.expert.data.source.remote.RemoteDataSource
import com.gaspol.expert.data.source.remote.RemoteDataSourceGCP
import com.gaspol.expert.data.source.remote.network.ApiResponse
import com.gaspol.expert.domain.model.CommodityItem
import com.gaspol.expert.domain.model.Country
import com.gaspol.expert.domain.model.Prediction
import com.gaspol.expert.domain.repository.IExpertRepository
import com.gaspol.expert.utils.AppExecutors
import com.gaspol.expert.utils.DataMapper
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import okhttp3.RequestBody

class ExpertRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val remoteDataSourceGCP: RemoteDataSourceGCP,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IExpertRepository {

    companion object {
        @Volatile
        private var instance: ExpertRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            remoteDataSourceGCP: RemoteDataSourceGCP,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): ExpertRepository =
            instance ?: synchronized(this) {
                instance ?: ExpertRepository(remoteData, remoteDataSourceGCP, localData, appExecutors)
            }
    }

    override fun getAll(): LiveData<List<RecentSearchEntity>> = localDataSource.getAll()

    override fun insert(recentSearchEntity: RecentSearchEntity){
        appExecutors.diskIO().execute { localDataSource.insert(recentSearchEntity) }
    }

    override fun delete(recentSearchEntity: RecentSearchEntity){
        appExecutors.diskIO().execute { localDataSource.delete(recentSearchEntity) }
    }

    override fun getAllCountries(): Flowable<Resource<List<Country>>> {
        val mCompositeDisposable = CompositeDisposable()
        val result = PublishSubject.create<Resource<List<Country>>>()
        val apiResponse = remoteDataSource.getAllCountries()
        result.onNext(Resource.Loading(null))
        val response = apiResponse
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .doOnComplete {
                mCompositeDisposable.dispose()
            }
            .subscribe { response ->
                when(response){
                    is ApiResponse.Success -> {
                        val mapperResult = DataMapper.mapResponsesToDomain(response.data)
                        result.onNext(Resource.Success(mapperResult))
                    }
                    is ApiResponse.Empty -> {
                        Log.d("getAllCountries", "empty")
                    }
                    is ApiResponse.Error -> {
                        Resource.Error<Any>(response.errorMessage)
                    }
                }
            }
        mCompositeDisposable.add(response)

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    override fun getPrediction(requestBody: RequestBody): Flowable<Resource<Prediction>> {
        val mCompositeDisposable = CompositeDisposable()
        val result = PublishSubject.create<Resource<Prediction>>()
        val apiResponse = remoteDataSourceGCP.getPredictions(requestBody)
        result.onNext(Resource.Loading(null))
        val response = apiResponse
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .doOnComplete {
                mCompositeDisposable.dispose()
            }
            .subscribe { response ->
                when(response){
                    is ApiResponse.Success -> {
                        val mapperResult = DataMapper.mapPredictionResponsesToDomain(response.data)
                        result.onNext(Resource.Success(mapperResult))
                    }
                    is ApiResponse.Empty -> {
                        Log.d("getPrediction", "empty")
                    }
                    is ApiResponse.Error -> {
                        Log.d("getPrediction", "error")
                    }
                }
            }
        mCompositeDisposable.add(response)

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    override suspend fun searchCommodity(search: String): List<CommodityItem>? {
        val result = remoteDataSource.searchCommodity(search)
        return DataMapper.mapCommodityResponsesToDomain(result.result)
    }
}