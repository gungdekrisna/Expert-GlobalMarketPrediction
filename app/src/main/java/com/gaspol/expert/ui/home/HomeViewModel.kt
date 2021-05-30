package com.gaspol.expert.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.gaspol.expert.data.source.local.entity.RecentSearchEntity
import com.gaspol.expert.data.source.remote.CommodityEntity
import com.gaspol.expert.domain.usecase.ExpertUseCase
import com.gaspol.expert.utils.DataDummy
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

class HomeViewModel(private val expertUseCase: ExpertUseCase) : ViewModel() {
    fun getRecentSearch(): LiveData<List<RecentSearchEntity>> = expertUseCase.getRecentSearch()

    fun insert(recentSearch: RecentSearchEntity){
        expertUseCase.insert(recentSearch)
    }
    fun delete(recentSearch: RecentSearchEntity){
        expertUseCase.delete(recentSearch)
    }

    fun getCommodities(): List<CommodityEntity> = DataDummy.generateDummyCommodity()

    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)

    val searchResult = queryChannel.asFlow()
        .debounce(300)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .mapLatest {
            expertUseCase.searchCommodity(it)
        }
        .asLiveData()
}