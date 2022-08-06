package com.athompson.virgin.data

import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("movielist.json")
    suspend fun getAllPeople() : Response<List<Person>>
    @GET("movielist.json")
    suspend fun getAllRooms() : Response<List<Room>>
}