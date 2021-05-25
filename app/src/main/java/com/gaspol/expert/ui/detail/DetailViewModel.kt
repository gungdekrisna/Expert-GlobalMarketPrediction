package com.gaspol.expert.ui.detail

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.gaspol.expert.domain.usecase.ExpertUseCase

class DetailViewModel(expertUseCase: ExpertUseCase) : ViewModel() {
    val prediction = LiveDataReactiveStreams.fromPublisher(expertUseCase.getPrediction())
}