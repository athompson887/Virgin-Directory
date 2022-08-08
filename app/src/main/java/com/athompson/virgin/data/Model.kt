package com.athompson.virgin.data

import com.google.gson.annotations.SerializedName

data class Room(
    val createdAt: String,
    val isOccupied: Boolean,
    val maxOccupancy: Int,
    val id:String)

data class Weather(
    @SerializedName("main") val temp: TempData,
    val name: String
)


data class TempData(
    val temp: Double,
    val humidity: Int
)


data class Person(
    val avatar: String,
    val createdAt: String,
    val `data`: Data,
    val email: String,
    val favouriteColor: String,
    val firstName: String,
    val fromName: String,
    val id: String,
    val jobtitle: String,
    val lastName: String,
    val to: String
)

data class Data(
    val body: String,
    val fromId: String,
    val id: String,
    val meetingid: String,
    val title: String,
    val toId: String
)