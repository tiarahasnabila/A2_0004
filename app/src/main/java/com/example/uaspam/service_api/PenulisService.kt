package com.example.uaspam.service_api

import com.example.uaspam.model.Penulis
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PenulisService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @POST("penulis/store")
    suspend fun insertPenulis(@Body penulis: Penulis)

    @GET("penulis")
    suspend fun getAllPenulis(): List<Penulis>

    @GET("penulis/{id_penulis}")
    suspend fun getPenulisbyId(@Path("id_penulis") id:String): Penulis

    @PUT("penulis/{id_penulis}")
    suspend fun updatePenulis(@Path("id_penulis") id:String, @Body penulis: Penulis)

    @DELETE("penulis/{id_penulis}")
    suspend fun deletePenulis(@Path("id_penulis") id:String): Response<Void>

}