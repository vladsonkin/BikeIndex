package com.vladsonkin.stolenbikes.domain.interactor.bike

import com.vladsonkin.stolenbikes.domain.executor.PostExecutionThread
import com.vladsonkin.stolenbikes.domain.executor.ThreadExecutor
import com.vladsonkin.stolenbikes.domain.interactor.UseCase
import com.vladsonkin.stolenbikes.domain.model.Bike
import com.vladsonkin.stolenbikes.domain.repository.BikeRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
* Created by Vlad Sonkin
* on 15 March 2018.
*/
open class SearchStolenBikes @Inject constructor(val bikeRepository: BikeRepository,
                                                 threadExecutor: ThreadExecutor,
                                                 postExecutionThread: PostExecutionThread) :
        UseCase<List<Bike>, SearchStolenBikes.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<List<Bike>> {
        return bikeRepository.searchStolenBikes(params.page, params.perPage, params.location, params.distance)
    }

    class Params private constructor(val page: Int, val perPage: Int, val location: String, val distance: String) {
        companion object {
            fun forData(page: Int, perPage: Int, location: String, distance: String) =
                    Params(page, perPage, location, distance)
        }
    }
}