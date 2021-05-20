package com.gaspol.expert.ui.country

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.gaspol.expert.domain.usecase.ExpertUseCase

class CountryViewModel(expertUseCase: ExpertUseCase) : ViewModel() {
    val countries = LiveDataReactiveStreams.fromPublisher(expertUseCase.getAllCountries())
}