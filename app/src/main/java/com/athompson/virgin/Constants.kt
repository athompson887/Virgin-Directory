package com.athompson.virgin

private const val baseURLLive = "https://61e947967bc0550017bc61bf.mockapi.io/api/v1/";
private const val baseURLTest = "https://61e947967bc0550017bc61bf.mockapi.io/api/v1/";
var testMood = false;

fun getURL(): String {
   return if(testMood) baseURLTest else baseURLLive
}

class Constants {


}