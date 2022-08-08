package com.athompson.virgin.networking

import com.athompson.virgin.PEOPLE
import com.athompson.virgin.ROOMS
import com.athompson.virgin.data.Person
import com.athompson.virgin.data.Room
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface RoomApi {

    @GET(ROOMS)
    suspend fun getRooms(): List<Room>
    @GET(PEOPLE)
    suspend fun getPeople(): List<Person>
}