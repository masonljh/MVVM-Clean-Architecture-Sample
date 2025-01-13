package com.antdev.sample.network

import retrofit2.Retrofit

object RetrofitClient {

    fun createRetrofitClient(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MyCustomConverterFactory()) // 커스텀 컨버터 팩토리 추가
            .build()
    }
}