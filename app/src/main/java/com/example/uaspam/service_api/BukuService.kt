package com.example.uaspam.service_api

import com.example.uaspam.model.Buku
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface BukuService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @POST("buku/store")
    suspend fun insertBuku(@Body buku: Buku)

    @GET("buku")
    suspend fun getAllBuku(): List<Buku>

    @GET("buku/{id_buku}")
    suspend fun getBukubyId(@Path("id_buku") id:String): Buku

    @PUT("buku/{id_buku}")
    suspend fun updateBuku(@Path("id_buku") id:String, @Body buku: Buku)

    @DELETE("buku/{id_buku}")
    suspend fun deleteBuku(@Path("id_buku") id:String): Response<Void>

}