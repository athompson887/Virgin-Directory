package com.athompson.virgin.data


import retrofit2.Response

interface MainRepository {
    suspend fun getAllRooms() : Response<List<Room>>
    suspend fun getAllPeople() : Response<List<Person>>
}
