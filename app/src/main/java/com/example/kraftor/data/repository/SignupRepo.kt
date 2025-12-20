package com.example.kraftor.data.repository

import com.example.kraftor.backend.dto.SignupRequest
import com.example.kraftor.backend.dto.SignupResponse
import com.example.kraftor.backend.network.APIService

/**
 * class for making the actual call to the signup API
 * and returning the result or handling any exceptions
 * @param APIService class object
 * @return SignupResponse class object
 * */
class SignupRepo(private val apiService: APIService){

    suspend fun signup(req: SignupRequest): Result<SignupResponse> {

        return runCatching{
            // return result of API call
            apiService.signup(req)
        }

    }

}