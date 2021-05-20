package com.gaspol.expert.domain.usecase

import androidx.lifecycle.LiveData
import com.gaspol.expert.data.Resource
import com.gaspol.expert.data.source.local.entity.RecentSearchEntity
import com.gaspol.expert.domain.model.Country
import io.reactivex.Flowable

interface ExpertUseCase {
    fun getRecentSearch(): LiveData<List<RecentSearchEntity>>
    fun insert(recentSearch: RecentSearchEntity)
    fun delete(recentSearch: RecentSearchEntity)
    fun getAllCountries(): Flowable<Resource<List<Country>>>
}