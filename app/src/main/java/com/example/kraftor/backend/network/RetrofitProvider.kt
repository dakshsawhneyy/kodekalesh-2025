package com.example.kraftor.backend.network

/**
 * Object to build the Base URL for the REST API Calls
 * @param -> context: Context
 * @return -> ApiService class object to make API calls
 * */

import android.content.Context

// class imports
import com.example.kraftor.data.local.TokenStorage

// HTTP requests
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

// making API calls
import retrofit2.Retrofit

//De-Serialization of JSONs
import com.squareup.moshi.Moshi
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

import kotlin.jvm.java

object RetrofitProvider {

    private const val BASE_URL = "https://rv6p8wji3k.execute-api.ap-south-1.amazonaws.com/kode-kalesh"

    fun createAPI(context: Context): APIService{

        // get user token
        val tokenStorage = TokenStorage(context.applicationContext)

        // create auth token interceptor
        val authInterceptor = AuthInterceptor(tokenStorage)

        // build HTTP Client for handling API calls
        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor) // add auth token to request header
            .connectTimeout(30, TimeUnit.SECONDS) // connection timeout limit
            .readTimeout(60, TimeUnit.SECONDS) // read timeout limit b/w packets
            .build()

        // build moshi object for JSON to dataclass conversion
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        // build retrofit object for making API routes
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(APIService :: class.java)

    }

}