package com.example.uaspam.repository

import com.example.uaspam.model.Penulis
import com.example.uaspam.service_api.PenulisService
import okio.IOException

interface PenulisRepository {
    suspend fun insertPenulis(penulis: Penulis)

    suspend fun getPenulis():List<Penulis>

    suspend fun updatePenulis(id: String,penulis: Penulis)

    suspend fun deletePenulis(id: String)

    suspend fun getPenulisbyId(id: String) : Penulis
}

class NetworkPenulisRepository(
    private val penulisApiService: PenulisService
): PenulisRepository {

    override suspend fun insertPenulis(penulis: Penulis) {
        penulisApiService.insertPenulis(penulis)
    }

    override suspend fun updatePenulis(id: String, penulis: Penulis) {
        penulisApiService.updatePenulis(id,penulis)
    }

    override suspend fun deletePenulis(id: String) {
        try {
            val response = penulisApiService.deletePenulis(id)
            if (!response.isSuccessful) {
                throw IOException(
                    "Failed to delete penulis. HTTP Status code:" +
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

    override suspend fun getPenulisbyId(id: String): Penulis {
        return penulisApiService.getPenulisbyId(id)
    }

    override suspend fun getPenulis(): List<Penulis> =
        penulisApiService.getAllPenulis()
    }


