package com.example.kraftor.data.repository

import com.example.kraftor.backend.dto.ReviewRequest
import com.example.kraftor.backend.dto.ReviewResponse
import com.example.kraftor.backend.network.APIService

/**
 * class for making the actual call to the generate text API
 * and returning the result or handling any exceptions
 * @param APIService class object
 * @return ReviewResponse class object
 * */
class ReviewRepo(private val apiService: APIService){

    suspend fun giveFeedback(req: ReviewRequest): Result<ReviewResponse> {

        return runCatching{
            // return result of API call
            apiService.postReview(req)
        }

    }

}