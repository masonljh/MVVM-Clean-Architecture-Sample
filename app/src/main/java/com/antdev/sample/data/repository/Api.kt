package com.antdev.sample.data.repository

import com.antdev.sample.domain.model.RequestDto
import com.antdev.sample.domain.model.ResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    @POST("/test")
    suspend fun getPersonList(@Body request: RequestDto): Response<ResponseDto>

}