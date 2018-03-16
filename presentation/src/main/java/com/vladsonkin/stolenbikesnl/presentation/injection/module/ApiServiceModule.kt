package com.vladsonkin.stolenbikesnl.presentation.injection.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vladsonkin.stolenbikesnl.data.api.BikeApiService
import com.vladsonkin.stolenbikesnl.presentation.injection.PerApplication
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Vlad Sonkin
 * on 15 March 2018.
 */
@Module
open class ApiServiceModule {

    @Provides
    @PerApplication
    fun provideBikeService(okHttpClient: OkHttpClient, gson: Gson): BikeApiService {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://bikeindex.org:443/api/v3/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(BikeApiService::class.java)
    }

    @Provides
    @PerApplication
    fun provideGson(): Gson = GsonBuilder()
            .setLenient()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    @Provides
    @PerApplication
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build()

    @Provides
    @PerApplication
    fun provideLoggingInterceptor() : HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}