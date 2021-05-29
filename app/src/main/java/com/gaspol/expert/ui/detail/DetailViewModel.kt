package com.gaspol.expert.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.gaspol.expert.data.Resource
import com.gaspol.expert.domain.model.Prediction
import com.gaspol.expert.domain.usecase.ExpertUseCase
import okhttp3.RequestBody

class DetailViewModel(private val expertUseCase: ExpertUseCase) : ViewModel() {
    fun prediction(requestBody: RequestBody) : LiveData<Resource<Prediction>> {
        return LiveDataReactiveStreams.fromPublisher(expertUseCase.getPrediction(requestBody))
    }
}