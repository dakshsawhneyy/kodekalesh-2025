package com.example.kraftor.data.repository

import com.example.kraftor.backend.dto.LoginRequest
import com.example.kraftor.backend.dto.LoginResponse
import com.example.kraftor.backend.network.APIService

/**
 * class for making the actual call to the login API
 * and returning the result or handling any exceptions
 * @param APIService class object
 * @return LoginResponse class object
 * */
class LoginRepo(private val apiService: APIService){

    suspend fun generateText(req: LoginRequest): Result<LoginResponse> {

        return runCatching{
            // return result of API call
            apiService.login(req)
        }

    }

}