package com.sonkins.bikeindex.data.repository

import com.sonkins.bikeindex.data.repository.datasource.BikeApiDataStore
import com.sonkins.bikeindex.domain.model.Bikes
import com.sonkins.bikeindex.domain.repository.BikeRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 15 March 2018.
 */
class BikeDataRepository @Inject constructor(
        private val bikeApiDataStore: BikeApiDataStore) : BikeRepository {

    override fun getBikes(page: Int,
                          perPage: Int,
                          location: String,
                          distance: String,
                          stolenness: String): Observable<Bikes> {
        return bikeApiDataStore.getBikes(page, perPage, location, distance, stolenness)
    }
}