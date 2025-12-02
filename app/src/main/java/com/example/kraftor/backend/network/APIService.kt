package com.example.kraftor.backend.network

// HTTP request imports
import com.example.kraftor.backend.dto.GenerateImageRequest
import com.example.kraftor.backend.dto.GenerateImageResponse
import com.example.kraftor.backend.dto.GenerateTextRequest
import com.example.kraftor.backend.dto.GenerateTextResponse
import com.example.kraftor.backend.dto.LoginRequest
import com.example.kraftor.backend.dto.LoginResponse
import com.example.kraftor.backend.dto.ReviewRequest
import com.example.kraftor.backend.dto.ReviewResponse
import com.example.kraftor.backend.dto.RewriteRequest
import com.example.kraftor.backend.dto.RewriteResponse
import com.example.kraftor.backend.dto.SignupRequest
import com.example.kraftor.backend.dto.SignupResponse
import  retrofit2.http.Body
import retrofit2.http.POST

interface APIService {

    @POST("login")
    suspend fun login(@Body req: LoginRequest): LoginResponse

    @POST("signup")
    suspend fun signup(@Body req: SignupRequest): SignupResponse

    @POST("generateText")
    suspend fun generateText(@Body req: GenerateTextRequest): GenerateTextResponse

    @POST("generateImage")
    suspend fun generateImage(@Body req: GenerateImageRequest): GenerateImageResponse

    @POST("rewriteForPlatform")
    suspend fun rewriteForPlatform(@Body req: RewriteRequest): RewriteResponse

    @POST("reviews")
    suspend fun postReview(@Body req: ReviewRequest): ReviewResponse

}