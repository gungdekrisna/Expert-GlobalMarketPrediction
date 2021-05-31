package com.gaspol.expert.domain.repository

import androidx.lifecycle.LiveData
import com.gaspol.expert.data.Resource
import com.gaspol.expert.data.source.local.entity.RecentSearchEntity
import com.gaspol.expert.domain.model.CommodityItem
import com.gaspol.expert.domain.model.Country
import com.gaspol.expert.domain.model.Prediction
import io.reactivex.Flowable
import okhttp3.RequestBody

interface IExpertRepository {
    fun getAll(): LiveData<List<RecentSearchEntity>>
    fun insert(recentSearch: RecentSearchEntity)
    fun delete(recentSearch: RecentSearchEntity)
    fun getAllCountries(): Flowable<Resource<List<Country>>>
    fun getPrediction(requestBody: RequestBody): Flowable<Resource<Prediction>>
    suspend fun searchCommodity(search: String): List<CommodityItem>?
}