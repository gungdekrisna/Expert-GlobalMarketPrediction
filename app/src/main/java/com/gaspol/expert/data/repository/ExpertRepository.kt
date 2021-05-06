package com.gaspol.expert.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.gaspol.expert.data.local.AppDatabase
import com.gaspol.expert.data.local.RecentSearchDao
import com.gaspol.expert.data.local.RecentSearchEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ExpertRepository constructor(application: Application) {
    private val recentSearchDao: RecentSearchDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = AppDatabase.getDatabase(application)
        recentSearchDao = db.recentSearchDao()
    }

    fun getAll(): LiveData<List<RecentSearchEntity>> = recentSearchDao.getAllRecentSearch()

    fun insert(recentSearchEntity: RecentSearchEntity){
        executorService.execute { recentSearchDao.insert(recentSearchEntity) }
    }

    fun delete(recentSearchEntity: RecentSearchEntity){
        executorService.execute { recentSearchDao.delete(recentSearchEntity) }
    }
}