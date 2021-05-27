package com.gaspol.expert.domain.usecase

import androidx.lifecycle.LiveData
import com.gaspol.expert.data.Resource
import com.gaspol.expert.data.source.local.entity.RecentSearchEntity
import com.gaspol.expert.domain.model.Country
import com.gaspol.expert.domain.model.Prediction
import com.gaspol.expert.domain.repository.IExpertRepository
import io.reactivex.Flowable
import okhttp3.RequestBody

class ExpertInteractor(private val expertRepository: IExpertRepository): ExpertUseCase {
    override fun getRecentSearch(): LiveData<List<RecentSearchEntity>> = expertRepository.getAll()

    override fun insert(recentSearch: RecentSearchEntity) = expertRepository.insert(recentSearch)

    override fun delete(recentSearch: RecentSearchEntity) = expertRepository.delete(recentSearch)
    override fun getAllCountries(): Flowable<Resource<List<Country>>> = expertRepository.getAllCountries()
    override fun getPrediction(requestBody: RequestBody): Flowable<Resource<Prediction>> = expertRepository.getPrediction(requestBody)
}