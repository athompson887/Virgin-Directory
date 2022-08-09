package com.athompson.virgin.networking

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        //Not using in this project
        val url = req.url.newBuilder().addQueryParameter("APPID", "TOKEN").build()
        req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}