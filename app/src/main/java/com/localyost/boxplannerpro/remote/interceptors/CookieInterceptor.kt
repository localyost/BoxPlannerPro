package com.localyost.boxplannerpro.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class CookieInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        // Retrieve the cookies from the response headers
        val cookies = response.headers("Set-Cookie")
        val authHeader = cookies.find { cookie -> cookie.startsWith(".ASPXAUTH") }
        return response;
    }
}