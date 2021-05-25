package com.gaspol.expert.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gaspol.expert.ui.detail.DetailViewModel
import com.gaspol.expert.di.Injection
import com.gaspol.expert.domain.usecase.ExpertUseCase
import com.gaspol.expert.ui.country.CountryViewModel
import com.gaspol.expert.ui.home.HomeViewModel

class ViewModelFactory private constructor(private val expertUseCase: ExpertUseCase) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideExpertUseCase(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(expertUseCase) as T
            }
            modelClass.isAssignableFrom(CountryViewModel::class.java) -> {
                CountryViewModel(expertUseCase) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(expertUseCase) as T
            }
            /*modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(tourismUseCase) as T
            }
            modelClass.isAssignableFrom(DetailTourismViewModel::class.java) -> {
                DetailTourismViewModel(tourismUseCase) as T
            }*/
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}