package com.example.uaspam.service_api

import com.example.uaspam.model.Buku
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface BukuService {
    @Headers(
        "Accept:application/json",
        "Content-Type:application/json",
    )

    @POST("buku")
    suspend fun insertBuku(@Body buku: Buku)

    @GET("buku")
    suspend fun getAllBuku(): List<Buku>

    @GET("buku/:id")
    suspend fun getBukubyId(@Query("id") id:String):Buku

    @PUT("buku/:id")
    suspend fun updateBuku(@Query("id") id:String, @Body buku: Buku)

    @DELETE("buku/:id")
    suspend fun deleteBuku(@Query("id") id:String): Response<Void>



}