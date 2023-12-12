package com.D121211069.catpedia.data.repository

import com.D121211069.catpedia.data.model.Cat
import com.D121211069.catpedia.data.source.remote.ApiService

class CatRepository(private val apiService: ApiService) {

    suspend fun getCats(): List<Cat> {
        return apiService.getCats()
    }

    suspend fun getCatDetail(id : String): Cat {
        return apiService.getCatDetail(id)
    }

    suspend fun getCatsSearch(id : String): List<Cat> {
        return apiService.getCatsSearch(id)
    }
}
