package com.athompson.virgin.data

import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("movielist.json")
    suspend fun getAllMovies() : Response<List<Movie>>
}