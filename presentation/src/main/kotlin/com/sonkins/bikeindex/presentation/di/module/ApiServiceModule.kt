package com.sonkins.bikeindex.presentation.di.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sonkins.bikeindex.data.api.BikeIndexApiService
import com.sonkins.bikeindex.presentation.di.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Named


/**
 * Created by Vlad Sonkin
 * on 15 March 2018.
 */
@Module
open class ApiServiceModule {

    @Provides
    @ApplicationScope
    @Named("client")
    fun provideBikeService(okHttpClient: OkHttpClient, gson: Gson): BikeIndexApiService {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://bikeindex.org:443/api/v3/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(BikeIndexApiService::class.java)
    }

    @Provides
    @ApplicationScope
    fun provideGson(): Gson = GsonBuilder()
            .setLenient()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build()

    @Provides
    @ApplicationScope
    fun provideLoggingInterceptor() : HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Timber.tag("OkHttp").i(it)
        })
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }
}