package com.gaspol.expert.data.source.local

import androidx.lifecycle.LiveData
import com.gaspol.expert.data.source.local.entity.RecentSearchEntity
import com.gaspol.expert.data.source.local.room.RecentSearchDao

class LocalDataSource private constructor(private val recentSearchDao: RecentSearchDao) {
    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(recentSearchDao: RecentSearchDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(recentSearchDao)
            }
    }

    fun getAll(): LiveData<List<RecentSearchEntity>> {
        return recentSearchDao.getAllRecentSearch()
    }

    fun insert(recentSearchEntity: RecentSearchEntity){
        recentSearchDao.insert(recentSearchEntity)
    }

    fun delete(recentSearchEntity: RecentSearchEntity){
        recentSearchDao.delete(recentSearchEntity)
    }
}