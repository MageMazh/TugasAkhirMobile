package com.D121211069.catpedia.data.source.remote

import com.D121211069.catpedia.data.model.Cat
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("breeds")
    suspend fun getCats(): List<Cat>

    @GET("breeds/{id}")
    suspend fun getCatDetail(
        @Path("id") id: String
    ): Cat

    @GET("breeds/search")
    suspend fun getCatsSearch(
        @Query("q") q: String
    ): List<Cat>
}
