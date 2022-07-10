package com.dicoding.tourismapp.core.domain.useCase

import com.dicoding.tourismapp.core.data.Resource
import com.dicoding.tourismapp.core.domain.model.Tourism
import com.dicoding.tourismapp.core.domain.repository.ITourismRepository
import io.reactivex.Flowable

class TourismInteractor(private val repository: ITourismRepository) : TourismUseCase {
    override fun getAllTourism(): Flowable<Resource<List<Tourism>>> = repository.getAllTourism()

    override fun getFavoriteTourism(): Flowable<List<Tourism>> = repository.getFavoriteTourism()

    override fun setFavoriteTourism(tourism: Tourism, state: Boolean) = repository.setFavoriteTourism(tourism, state)
}