package com.application.capstone.care.api.retrofit

import com.application.capstone.care.api.response.EmotionResponse
import com.application.capstone.care.api.response.LoginRequest
import com.application.capstone.care.api.response.LoginResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST("predict_emotion")
    suspend fun predictEmotion(
        @Part image: MultipartBody.Part
    ): Response<EmotionResponse>

    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>
}