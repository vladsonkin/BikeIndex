package com.sonkins.bikeindex.data.repository

import com.sonkins.bikeindex.data.mapper.BikesMapper
import com.sonkins.bikeindex.data.repository.datasource.BikeApiDataStore
import com.sonkins.bikeindex.domain.model.Bike
import com.sonkins.bikeindex.domain.repository.BikeRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 15 March 2018.
 */
class BikeDataRepository @Inject constructor(
        private val bikeApiDataStore: BikeApiDataStore,
        private val bikesMapper: BikesMapper) : BikeRepository {

    override fun getBikes(page: Int,
                          perPage: Int,
                          location: String,
                          distance: String,
                          stolenness: String): Observable<List<Bike>> {
        return bikeApiDataStore.getBikes(page, perPage, location, distance, stolenness)
                .map(bikesMapper::transform)
    }
}