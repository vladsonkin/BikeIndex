package com.sonkins.bikeindex.domain.interactor.filter

import com.sonkins.bikeindex.domain.interactor.UseCase
import com.sonkins.bikeindex.domain.model.Colors
import com.sonkins.bikeindex.domain.repository.BikeRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 13 April 2018.
 */
open class GetColors @Inject constructor(private val bikeRepository: BikeRepository) : UseCase<Colors, Void?>() {

    override fun buildUseCaseObservable(params: Void?): Observable<Colors> {
        return bikeRepository.getColors()
    }

}