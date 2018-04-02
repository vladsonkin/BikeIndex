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
open class GetBikes @Inject constructor(val bikeRepository: BikeRepository,
                                        threadExecutor: ThreadExecutor,
                                        postExecutionThread: PostExecutionThread) :
        UseCase<List<Bike>, GetBikes.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<List<Bike>> {
        return bikeRepository.getBikes(
                params.page,
                10,
                "52.379189,4.899431",
                "150",
                "proximity")
    }

    class Params private constructor(val page: Int) {
        companion object {
            fun forData(page: Int) = Params(page)
        }
    }
}