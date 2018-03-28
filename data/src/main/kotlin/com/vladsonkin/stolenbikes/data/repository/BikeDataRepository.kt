package com.vladsonkin.stolenbikes.data.repository

import com.vladsonkin.stolenbikes.domain.model.Bike
import com.vladsonkin.stolenbikes.domain.repository.BikeRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 15 March 2018.
 */
class BikeDataRepository @Inject constructor() : BikeRepository {
    override fun searchStolenBikes(page: Int, perPage: Int, location: String, distance: String): Observable<List<Bike>> {

        var bikes = ArrayList<Bike>()

        bikes.add(Bike(1, "Big bike"))
        bikes.add(Bike(2, "Black metal bike"))
        bikes.add(Bike(3, "Pinky kids bike"))
        bikes.add(Bike(4, "Old bike"))
        bikes.add(Bike(5, "Monster track"))

        return Observable.just(bikes)
    }
}