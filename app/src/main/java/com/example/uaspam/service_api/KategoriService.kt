package com.example.uaspam.service_api

import com.example.uaspam.model.Kategori
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface KategoriService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @POST("kategori/store")
    suspend fun insertKategori(@Body kategori: Kategori)

    @GET("kategori")
    suspend fun getAllKategori(): List<Kategori>

    @GET("kategori/{id_kategori}")
    suspend fun getKategoribyId(@Path("id_kategori") id:String): Kategori

    @PUT("kategori/{id_kategori")
    suspend fun updateKategori(@Path("id_kategori") id:String, @Body kategori: Kategori)

    @DELETE("kategori/{id_kategori}")
    suspend fun deleteKategori(@Path("id_kategori") id:String): Response<Void>
}
