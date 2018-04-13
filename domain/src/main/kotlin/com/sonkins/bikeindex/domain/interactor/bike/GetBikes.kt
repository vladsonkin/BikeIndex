package com.sonkins.bikeindex.domain.interactor.bike

import com.sonkins.bikeindex.domain.interactor.UseCase
import com.sonkins.bikeindex.domain.model.Bikes
import com.sonkins.bikeindex.domain.model.Filter
import com.sonkins.bikeindex.domain.repository.BikeRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
* Created by Vlad Sonkin
* on 15 March 2018.
*/
open class GetBikes @Inject constructor(private val bikeRepository: BikeRepository)
    : UseCase<Bikes, GetBikes.Params>() {

    override fun buildUseCaseObservable(params: Params): Observable<Bikes> {
        return bikeRepository.getBikes(
                params.filter.page,
                params.filter.perPage)
    }

    class Params private constructor(val filter: Filter) {
        companion object {
            fun forFilter(filter: Filter) = Params(filter)
        }
    }
}