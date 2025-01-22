package com.example.uaspam.service_api

import com.example.uaspam.model.Penulis
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface PenulisService {
    @Headers(
        "Accept : application/json",
        "Content-Type : application/json",
    )

    @POST("penulis")
    suspend fun insertPenulis(@Body penulis: Penulis)

    @GET("penulis")
    suspend fun getAllPenulis(): List<Penulis>

    @GET("penulis/:id")
    suspend fun getPenulisbyId(@Query("id") id:String): Penulis

    @PUT("penulis/:id")
    suspend fun updatePenulis(@Query("id") id:String, @Body penulis: Penulis)

    @DELETE("penulis/:id")
    suspend fun deletePenulis(@Query("id") id:String): Response<Void>

}