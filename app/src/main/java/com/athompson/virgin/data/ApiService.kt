package com.athompson.virgin.data

import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("people")
    suspend fun getAllPeople() : Response<List<Person>>
    @GET("rooms")
    suspend fun getAllRooms() : Response<List<Room>>
}