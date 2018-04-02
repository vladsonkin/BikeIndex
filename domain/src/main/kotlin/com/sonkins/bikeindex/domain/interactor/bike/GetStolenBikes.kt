package com.sonkins.bikeindex.domain.interactor.bike

import com.sonkins.bikeindex.domain.executor.PostExecutionThread
import com.sonkins.bikeindex.domain.executor.ThreadExecutor
import com.sonkins.bikeindex.domain.interactor.UseCase
import com.sonkins.bikeindex.domain.model.Bike
import com.sonkins.bikeindex.domain.repository.BikeRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
* Created by Vlad Sonkin
* on 15 March 2018.
*/
open class GetStolenBikes @Inject constructor(val bikeRepository: BikeRepository,
                                              threadExecutor: ThreadExecutor,
                                              postExecutionThread: PostExecutionThread) :
        UseCase<List<Bike>, GetStolenBikes.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<List<Bike>> {
        return bikeRepository.getStolenBikes(params.page, params.perPage, params.location, "150", "proximity")
    }

    class Params private constructor(val page: Int, val perPage: Int, val location: String) {
        companion object {
            fun forData(page: Int, perPage: Int, location: String) =
                    Params(page, perPage, location)
        }
    }
}