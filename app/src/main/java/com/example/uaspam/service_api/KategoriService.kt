package com.example.uaspam.service_api

import com.example.uaspam.model.Kategori
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface KategoriService {
    @Headers(
        "Accept : application/json",
        "Content-Type : application/json",
    )

    @POST("kategori")
    suspend fun insertKategori(@Body kategori: Kategori)

    @GET("kategori")
    suspend fun getAllKategori(): List<Kategori>

    @GET("kategori/:id")
    suspend fun getKategoribyId(@Query("id") id:String): Kategori

    @PUT("kategori/:id")
    suspend fun updateKategori(@Query("id") id:String, @Body kategori: Kategori)

    @DELETE("kategori/:id")
    suspend fun deleteKategori(@Query("id") id:String): Response<Void>
}
