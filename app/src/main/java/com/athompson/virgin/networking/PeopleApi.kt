package com.athompson.virgin.networking
import com.athompson.virgin.PEOPLE
import com.athompson.virgin.data.Person
import retrofit2.http.GET

interface PeopleApi {
    @GET(PEOPLE)
    suspend fun getPeople(): List<Person>
}