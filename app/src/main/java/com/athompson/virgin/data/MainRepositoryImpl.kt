package com.athompson.virgin.data

import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val apiService: ApiService): MainRepository {
    override suspend fun getAllMovies() = apiService.getAllMovies()
}

