package com.example.uaspam.service_api

import com.example.uaspam.model.Penerbit
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PenerbitService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @POST("penerbit/store")
    suspend fun insertPenerbit(@Body penerbit: Penerbit)

    @GET("penerbit")
    suspend fun getAllPenerbit(): List<Penerbit>

    @GET("penerbit/{id_penerbit}")
    suspend fun getPenerbitbyId(@Path("id_penerbit") id:String): Penerbit

    @PUT("penerbit/{id_penerbit}")
    suspend fun updatePenerbit(@Path("id_penerbit") id:String, @Body penerbit: Penerbit)

    @DELETE("penerbit/{id_penerbit}")
    suspend fun deletePenerbit(@Path("id_penerbit") id:String): Response<Void>
}