package com.athompson.virgin.data

import com.athompson.virgin.PEOPLE
import com.athompson.virgin.ROOMS
import com.athompson.virgin.getURL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET(ROOMS)
    fun getRooms() : Call<List<Room>>

    @GET(PEOPLE)
    fun getPeople() : Call<List<Person>>

    companion object {

        var BASE_URL = getURL()
        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}


