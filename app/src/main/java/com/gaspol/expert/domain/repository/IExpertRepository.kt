package com.gaspol.expert.domain.repository

import androidx.lifecycle.LiveData
import com.gaspol.expert.data.Resource
import com.gaspol.expert.data.source.local.entity.RecentSearchEntity
import com.gaspol.expert.domain.model.Country
import io.reactivex.Flowable

interface IExpertRepository {
    fun getAll(): LiveData<List<RecentSearchEntity>>
    fun insert(recentSearchEntity: RecentSearchEntity)
    fun delete(recentSearchEntity: RecentSearchEntity)
    fun getAllCountries(): Flowable<Resource<List<Country>>>
}