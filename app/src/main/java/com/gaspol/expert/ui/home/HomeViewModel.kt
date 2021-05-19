package com.gaspol.expert.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gaspol.expert.data.source.local.entity.RecentSearchEntity
import com.gaspol.expert.data.source.remote.CommodityEntity
import com.gaspol.expert.domain.usecase.ExpertUseCase
import com.gaspol.expert.utils.DataDummy

class HomeViewModel(private val expertUseCase: ExpertUseCase) : ViewModel() {
    fun getRecentSearch(): LiveData<List<RecentSearchEntity>> = expertUseCase.getRecentSearch()

    fun insert(recentSearch: RecentSearchEntity){
        expertUseCase.insert(recentSearch)
    }
    fun delete(recentSearch: RecentSearchEntity){
        expertUseCase.delete(recentSearch)
    }

    fun getCommodities(): List<CommodityEntity> = DataDummy.generateDummyCommodity()
}