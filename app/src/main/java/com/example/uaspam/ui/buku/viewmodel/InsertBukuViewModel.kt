package com.example.uaspam.ui.buku.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Buku
import com.example.uaspam.repository.BukuRepository
import kotlinx.coroutines.launch

class InsertBukuViewModel (private val buku: BukuRepository): ViewModel(){
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertBukuState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertBuku(){
        viewModelScope.launch {
            try {
                buku.insertBuku(uiState.insertUiEvent.toBuku())
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    val idBuku: Int = 0,
    val namaBuku: String = "",
    val deskripsiBuku: String = "",
    val tanggalTerbit: String = "",
    val statusBuku: String = "",
    val idKategori: Int = 0 ,
    val idPenulis: Int = 0,
    val idPenerbit: Int = 0
)

fun InsertUiEvent.toBuku():Buku = Buku(
    idBuku = idBuku,
    namaBuku = namaBuku,
    deskripsiBuku = deskripsiBuku,
    tanggalTerbit = tanggalTerbit,
    statusBuku = statusBuku,
    idKategori = idKategori,
    idPenulis = idPenulis,
    idPenerbit = idPenerbit
)

fun Buku.toUiStateBuku():InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Buku.toInsertUiEvent():InsertUiEvent = InsertUiEvent(
    idBuku = idBuku,
    namaBuku = namaBuku,
    deskripsiBuku = deskripsiBuku,
    tanggalTerbit = tanggalTerbit,
    statusBuku = statusBuku,
    idKategori = idKategori,
    idPenulis = idPenulis,
    idPenerbit = idPenerbit
)
