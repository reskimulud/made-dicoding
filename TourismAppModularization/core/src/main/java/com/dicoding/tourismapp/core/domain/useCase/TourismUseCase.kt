package com.dicoding.tourismapp.core.domain.useCase

import com.dicoding.tourismapp.core.data.Resource
import com.dicoding.tourismapp.core.domain.model.Tourism
import kotlinx.coroutines.flow.Flow

interface TourismUseCase {
    fun getAllTourism() : Flow<com.dicoding.tourismapp.core.data.Resource<List<Tourism>>>
    fun getFavoriteTourism() : Flow<List<Tourism>>
    fun setFavoriteTourism(tourism: Tourism, state: Boolean)
}