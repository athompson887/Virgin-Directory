package com.athompson.virgin.networking

import com.athompson.virgin.ROOMS
import com.athompson.virgin.data.Room
import retrofit2.http.GET

interface RoomApi {
    @GET(ROOMS)
    suspend fun getRooms(): List<Room>
}