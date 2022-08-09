package com.athompson.virgin.repository
import com.athompson.virgin.data.Person
import com.athompson.virgin.networking.PeopleApi
import com.athompson.virgin.networking.Resource
import com.athompson.virgin.networking.ResponseHandler


open class PeopleRepository(
    private val peopleApi: PeopleApi,
    private val responseHandler: ResponseHandler
) {

    suspend fun getPeople(): Resource<List<Person>> {
        return try {
            val response = peopleApi.getPeople()
            return responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}
