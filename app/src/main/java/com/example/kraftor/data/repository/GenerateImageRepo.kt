package com.example.kraftor.data.repository

import com.example.kraftor.backend.dto.GenerateImageRequest
import com.example.kraftor.backend.dto.GenerateImageResponse
import com.example.kraftor.backend.network.APIService

/**
 * class for making the actual call to the generate image API
 * and returning the result or handling any exceptions
 * @param  APIService class object
 * @return  GenerateImageResponse class object
 * */
class GenerateImageRepo(private val apiService: APIService){

    suspend fun generateImage(req: GenerateImageRequest): Result<GenerateImageResponse> {

        return runCatching{
            // return result of API call
            apiService.generateImage(req)
        }

    }

}