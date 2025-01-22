package com.example.uaspam.repository

import com.example.uaspam.model.Penerbit
import com.example.uaspam.service_api.PenerbitService
import okio.IOException

interface PenerbitRepository {
    suspend fun insertPenerbit(penerbit: Penerbit)

    suspend fun getPenerbit():List<Penerbit>

    suspend fun updatePenerbit(id: String,penerbit: Penerbit)

    suspend fun deletePenerbit(id: String)

    suspend fun getPenerbitbyId(id: String) : Penerbit
}

class NetworkPenerbitRepository(
    private val penerbitApiService: PenerbitService
): PenerbitRepository {

    override suspend fun insertPenerbit(penerbit: Penerbit) {
        penerbitApiService.insertPenerbit(penerbit)
    }

    override suspend fun getPenerbit(): List<Penerbit> {
        return penerbitApiService.getAllPenerbit()
    }

    override suspend fun updatePenerbit(id: String, penerbit: Penerbit) {
        penerbitApiService.updatePenerbit(id, penerbit)
    }

    override suspend fun deletePenerbit(id: String) {
        try {
            val response = penerbitApiService.deletePenerbit(id)
            if (!response.isSuccessful) {
                throw IOException(
                    "Failed to delete penerbit. HTTP Status code:" +
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

    override suspend fun getPenerbitbyId(id: String): Penerbit {
        return penerbitApiService.getPenerbitbyId(id)
    }

}