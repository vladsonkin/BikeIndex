package com.vladsonkin.stolenbikes.data.repository

import com.vladsonkin.stolenbikes.data.mapper.BikesMapper
import com.vladsonkin.stolenbikes.data.repository.datasource.BikeApiDataStore
import com.vladsonkin.stolenbikes.domain.model.Bike
import com.vladsonkin.stolenbikes.domain.repository.BikeRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 15 March 2018.
 */
class BikeDataRepository @Inject constructor(
        private val bikeApiDataStore: BikeApiDataStore,
        private val bikesMapper: BikesMapper) : BikeRepository {

    override fun getStolenBikes(page: Int,
                                perPage: Int,
                                location: String,
                                distance: String,
                                stolenness: String): Observable<List<Bike>> {
        return bikeApiDataStore.getStolenBikes(page, perPage, location, distance, stolenness)
                .map(bikesMapper::transform)
    }
}