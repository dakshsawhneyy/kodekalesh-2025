package com.example.kraftor.data.repository

import com.example.kraftor.backend.dto.RewriteRequest
import com.example.kraftor.backend.dto.RewriteResponse
import com.example.kraftor.backend.network.APIService
import com.example.kraftor.backend.network.CategoryStore

/**
 * class for making the actual call to the rewrite API
 * and returning the result or handling any exceptions
 * @param APIService class object
 * @return RewriteResponse class object
 * */
class RewriteTextRepo(private val apiService: APIService){

    suspend fun RewriteText(req: RewriteRequest): Result<RewriteResponse> {

        return runCatching{
            // return result of API call
            apiService.rewriteForPlatform(req)
        }

    }

}