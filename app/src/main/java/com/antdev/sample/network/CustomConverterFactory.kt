package com.antdev.sample.network

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.Type

class MyCustomConverterFactory : Converter.Factory() {

    private val gson = Gson()

    // 응답(ResponseBody)을 객체로 변환하는 메소드
    override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit): Converter<ResponseBody, *> {
        return Converter { value ->
            // ResponseBody가 비어있는지 확인
            if (value.contentLength() == 0L) {
                // 응답 내용이 없으면 null을 반환하거나 기본값을 반환
                return@Converter null
            }

            try {
                // JSON을 객체로 변환
                val json = value.string()
                val typeToken = TypeToken.get(type)
                if (json.startsWith("{") || json.startsWith("[")) {
                    // JSON 문자열을 해당 객체로 변환
                    return@Converter gson.fromJson(json, typeToken)
                } else {
                    throw IOException("응답이 유효한 JSON 형식이 아닙니다.")
                }
            } catch (e: JsonSyntaxException) {
                // JSON 파싱 오류 발생 시 처리
                throw IOException("응답이 유효한 JSON 형식이 아닙니다.", e)
            }
        }
    }

    // 요청(RequestBody)을 객체로 변환하는 메소드
    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<out Annotation>,
        methodAnnotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        return Converter<Any, RequestBody> { value -> // 요청 객체를 JSON으로 변환
            val json = Gson().toJson(value)

            // MediaType을 명시적으로 지정하여 RequestBody 생성
            val mediaType = MediaType.parse("application/json")
            RequestBody.create(mediaType, json)
        }
    }
}