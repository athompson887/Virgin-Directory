package com.athompson.virgin.repository
import com.athompson.virgin.data.Room
import com.athompson.virgin.networking.Resource
import com.athompson.virgin.networking.ResponseHandler
import com.athompson.virgin.networking.RoomApi

open class RoomRepository(
    private val roomApi: RoomApi,
    private val responseHandler: ResponseHandler
) {

    suspend fun getRooms(): Resource<List<Room>> {
        return try {
            val response = roomApi.getRooms()
            return responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}
