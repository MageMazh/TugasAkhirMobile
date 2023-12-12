package com.D121211069.catpedia.data

import com.D121211069.catpedia.data.repository.CatRepository
import com.D121211069.catpedia.data.source.remote.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val catRepository: CatRepository
}

class DefaultAppContainer: AppContainer {

    private val BASE_URL = "https://api.thecatapi.com/v1/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val catRepository: CatRepository by lazy {
        CatRepository(retrofitService)
    }
}
