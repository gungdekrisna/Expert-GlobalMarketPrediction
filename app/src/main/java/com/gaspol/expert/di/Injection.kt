package com.gaspol.expert.di

import android.content.Context
import com.gaspol.expert.data.ExpertRepository
import com.gaspol.expert.data.source.local.LocalDataSource
import com.gaspol.expert.data.source.local.room.AppDatabase
import com.gaspol.expert.data.source.remote.RemoteDataSource
import com.gaspol.expert.data.source.remote.RemoteDataSourceGCP
import com.gaspol.expert.data.source.remote.network.ApiConfig
import com.gaspol.expert.domain.repository.IExpertRepository
import com.gaspol.expert.domain.usecase.ExpertInteractor
import com.gaspol.expert.domain.usecase.ExpertUseCase
import com.gaspol.expert.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): IExpertRepository {
        val database = AppDatabase.getDatabase(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val remoteDataSourceGCP = RemoteDataSourceGCP.getInstance(ApiConfig.provideApiServiceGCP())
        val localDataSource = LocalDataSource.getInstance(database.recentSearchDao())
        val appExecutors = AppExecutors()

        return ExpertRepository.getInstance(remoteDataSource, remoteDataSourceGCP, localDataSource, appExecutors)
    }

    fun provideExpertUseCase(context: Context): ExpertUseCase {
        val repository = provideRepository(context)
        return ExpertInteractor(repository)
    }
}