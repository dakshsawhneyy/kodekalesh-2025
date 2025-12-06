package com.example.kraftor.data.repository

import com.example.kraftor.backend.dto.GenerateTextRequest
import com.example.kraftor.backend.dto.GenerateTextResponse
import com.example.kraftor.backend.network.APIService
import com.example.kraftor.backend.network.CategoryStore

/**
 * class for making the actual call to the generate text API
 * and returning the result or handling any exceptions
 * @param  APIService class object
 * @return  GenerateTextResponse class object
 * */
class GenerateTextRepo(private val apiService: APIService){

    suspend fun generateText(req: GenerateTextRequest): Result<GenerateTextResponse> {

        return runCatching{
            // set category store object for request category
            CategoryStore.set(req.category)
            // return result of API call
            apiService.generateText(req)
        }.also {
            // clear category store object
            CategoryStore.clear()
        }

    }

}