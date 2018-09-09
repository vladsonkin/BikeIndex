package com.sonkins.bikeindex.features.bike

/**
 * Exception when someone trying to register a new bike on the bikeindex.org and our deeplink fires
 * Throw an exception because we can't register a bike yet
 */
class BikeRegistrationErrorException(override val message: String) : Exception()