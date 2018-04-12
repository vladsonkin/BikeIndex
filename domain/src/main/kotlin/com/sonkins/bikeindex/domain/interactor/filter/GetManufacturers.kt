package com.sonkins.bikeindex.domain.interactor.filter

import com.sonkins.bikeindex.domain.interactor.UseCase
import com.sonkins.bikeindex.domain.model.Manufacturers
import com.sonkins.bikeindex.domain.repository.BikeRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 12 April 2018.
 */
open class GetManufacturers @Inject constructor(private val bikeRepository: BikeRepository)
    : UseCase<Manufacturers, GetManufacturers.Params>() {

    override fun buildUseCaseObservable(params: Params): Observable<Manufacturers> {
        return bikeRepository.getManufacturers(params.page, 20)
    }

    class Params private constructor(val page: Int) {
        companion object {
            fun forPage(page: Int) = Params(page)
        }
    }
}