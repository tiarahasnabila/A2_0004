package com.example.uaspam.repository

import com.example.uaspam.model.Buku
import com.example.uaspam.model.Kategori
import com.example.uaspam.service_api.BukuService
import com.example.uaspam.service_api.KategoriService
import okio.IOException

interface KategoriRepository {
    suspend fun insertKategori(kategori: Kategori)

    suspend fun getKategori():List<Kategori>

    suspend fun updateKategori(id: String,kategori: Kategori)

    suspend fun deleteKategori(id: String)

    suspend fun getKategoribyId(id: String) : Kategori
}

class NetworkKategoriRepository(
    private val kategoriApiService: KategoriService
): KategoriRepository {

    override suspend fun insertKategori(kategori: Kategori) {
        kategoriApiService.insertKategori(kategori)
    }

    override suspend fun getKategori(): List<Kategori> {
        return kategoriApiService.getAllKategori()
    }

    override suspend fun updateKategori(id: String, kategori: Kategori) {
        kategoriApiService.updateKategori(id, kategori)
    }

    override suspend fun deleteKategori(id: String) {
        try {
            val response = kategoriApiService.deleteKategori(id)
            if (!response.isSuccessful) {
                throw IOException(
                    "Failed to delete kategori. HTTP Status code:" +
                            "${response.code()}"
                )
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getKategoribyId(id: String): Kategori {
        return kategoriApiService.getKategoribyId(id)
    }
}