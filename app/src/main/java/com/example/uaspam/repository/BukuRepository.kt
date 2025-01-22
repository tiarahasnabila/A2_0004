package com.example.uaspam.repository

import com.example.uaspam.model.Buku
import com.example.uaspam.service_api.BukuService
import okio.IOException

interface BukuRepository {
    suspend fun insertBuku(buku: Buku)

    suspend fun getBuku():List<Buku>

    suspend fun updateBuku(id: String,buku: Buku)

    suspend fun deleteBuku(id: String)

    suspend fun getBukubyId(id: String) : Buku
}

class NetworkBukuRepository(
    private val bukuApiService: BukuService
): BukuRepository {

    override suspend fun insertBuku(buku: Buku) {
        bukuApiService.insertBuku(buku)
    }

    override suspend fun updateBuku(id: String, buku: Buku) {
        bukuApiService.updateBuku(id, buku)
    }

    override suspend fun deleteBuku(id: String) {
        try {
            val response = bukuApiService.deleteBuku(id)
            if (!response.isSuccessful) {
                throw IOException(
                    "Failed to delete buku. HTTP Status code:" +
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

    override suspend fun getBukubyId(id: String): Buku {
        return bukuApiService.getBukubyId(id)
    }

    override suspend fun getBuku(): List<Buku> {
        return bukuApiService.getAllBuku()
    }
}

