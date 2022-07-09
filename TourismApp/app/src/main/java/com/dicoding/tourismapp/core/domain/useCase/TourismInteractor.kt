package com.dicoding.tourismapp.core.domain.useCase

import androidx.lifecycle.LiveData
import com.dicoding.tourismapp.core.data.Resource
import com.dicoding.tourismapp.core.domain.model.Tourism
import com.dicoding.tourismapp.core.domain.repository.ITourismRepository

class TourismInteractor(private val repository: ITourismRepository) : TourismUseCase {
    override fun getAllTourism(): LiveData<Resource<List<Tourism>>> = repository.getAllTourism()

    override fun getFavoriteTourism(): LiveData<List<Tourism>> = repository.getFavoriteTourism()

    override fun setFavoriteTourism(tourism: Tourism, state: Boolean) = repository.setFavoriteTourism(tourism, state)
}