package com.gaspol.expert.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gaspol.expert.data.local.RecentSearchEntity
import com.gaspol.expert.data.remote.CommodityEntity
import com.gaspol.expert.data.repository.ExpertRepository
import com.gaspol.expert.utils.DataDummy

class HomeViewModel(application: Application) : ViewModel() {
    private val expertRepository: ExpertRepository = ExpertRepository(application)
    // fun getRecentSearch(): List<RecentSearchEntity> = DataDummy.generateDummyRecentSearch()

    fun getRecentSearch(): LiveData<List<RecentSearchEntity>> = expertRepository.getAll()

    fun insert(recentSearch: RecentSearchEntity){
        expertRepository.insert(recentSearch)
    }
    fun delete(recentSearch: RecentSearchEntity){
        expertRepository.delete(recentSearch)
    }

    fun getCommodities(): List<CommodityEntity> = DataDummy.generateDummyCommodity()
}