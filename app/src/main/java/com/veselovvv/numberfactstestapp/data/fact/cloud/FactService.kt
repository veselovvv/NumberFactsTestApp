package com.veselovvv.numberfactstestapp.data.fact.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface FactService {
    @GET("{number}")
    suspend fun fetchFact(@Path("number") number: Int): ResponseBody

    @GET("random/math")
    suspend fun fetchRandomFact(): ResponseBody
}