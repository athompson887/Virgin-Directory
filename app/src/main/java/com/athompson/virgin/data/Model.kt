package com.athompson.virgin.data

data class Room(
    val createdAt: String,
    val isOccupied: Boolean,
    val maxOccupancy: Int,
    val id:String)

data class Person(
    val createdAt: String,
    val firstName: String,
    val avatar: String,
    val lastName: String,
    val email: String,
    val jobtitle: String,
    val favouriteColor: String,
    val id:String)