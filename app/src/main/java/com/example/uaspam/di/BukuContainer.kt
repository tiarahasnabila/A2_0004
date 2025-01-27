package com.example.uaspam.di

import com.example.uaspam.repository.BukuRepository
import com.example.uaspam.repository.KategoriRepository
import com.example.uaspam.repository.NetworkBukuRepository
import com.example.uaspam.repository.NetworkKategoriRepository
import com.example.uaspam.repository.NetworkPenerbitRepository
import com.example.uaspam.repository.NetworkPenulisRepository
import com.example.uaspam.repository.PenerbitRepository
import com.example.uaspam.repository.PenulisRepository
import com.example.uaspam.service_api.BukuService
import com.example.uaspam.service_api.KategoriService
import com.example.uaspam.service_api.PenerbitService
import com.example.uaspam.service_api.PenulisService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit


interface AppContainer{
    val bukuRepository: BukuRepository
    val penulisRepository :  PenulisRepository
    val penerbitRepository : PenerbitRepository
    val kategoriRepository : KategoriRepository
}

class BukuContainer : AppContainer {
    private val baseUrl = "http://10.0.2.2:3000/api/buku/" //localhost diganti ip kalau run di hp
    private val json = Json { ignoreUnknownKeys = true }

    val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).client(client).build()

    private val bukuApiService: BukuService by lazy {
        retrofit.create(BukuService::class.java)
    }

    override val bukuRepository: BukuRepository by lazy {
        NetworkBukuRepository(bukuApiService)
    }

    private val penulisApiService: PenulisService by lazy {
        retrofit.create(PenulisService::class.java)
    }

    override val penulisRepository: PenulisRepository by lazy {
        NetworkPenulisRepository(penulisApiService)
    }

    private val penerbitApiService: PenerbitService by lazy {
        retrofit.create(PenerbitService::class.java)
    }

    override val penerbitRepository: PenerbitRepository by lazy {
        NetworkPenerbitRepository(penerbitApiService)
    }

    private val kategoriApiService: KategoriService by lazy {
        retrofit.create(KategoriService::class.java)
    }

    override val kategoriRepository: KategoriRepository by lazy {
        NetworkKategoriRepository(kategoriApiService)
    }
}


