package com.example.kraftor.backend.network

// class imports
import com.example.kraftor.data.local.TokenStorage

// HTTP request imports
import okhttp3.Interceptor
import okhttp3.Response

/**
 * class to authorize the user request by adding user auth token to the request header
 * @param -> tokenStorage: TokenStorage class object containing user auth token
 * @return -> Interceptor class object with modified request header
 */
class AuthInterceptor(private val tokenStorage: TokenStorage): Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {

        // get original request chain
        val original = chain.request()

        // create new request chain from original
        val builder  = original.newBuilder()

        // get auth token from local storage (null) if logged out
        val token = tokenStorage.getToken()?.let{ token->
            // add to request header
            builder.addHeader("Authorization", "$token")
        }

        // build new request
        val request = builder.build()

        // proceed further down request chain
        return chain.proceed(request)
    }

}