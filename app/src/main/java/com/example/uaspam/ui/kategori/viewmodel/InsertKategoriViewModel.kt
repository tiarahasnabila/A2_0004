package com.example.uaspam.ui.kategori.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Kategori
import com.example.uaspam.repository.KategoriRepository
import kotlinx.coroutines.launch

class InsertKategoriViewModel (private val kategori: KategoriRepository): ViewModel(){
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertKategoriState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertKategori(){
        viewModelScope.launch {
            try {
                kategori.insertKategori(uiState.insertUiEvent.toKategori())
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
    val idKategori: Int = 0,
    val namaKategori: String = "",
    val deskripsiKategori: String = ""
)

fun InsertUiEvent.toKategori(): Kategori = Kategori(
    idKategori = idKategori,
    namaKategori = namaKategori,
    deskripsiKategori = deskripsiKategori
)

fun Kategori.toUiStateKategori():InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Kategori.toInsertUiEvent():InsertUiEvent = InsertUiEvent(
    idKategori = idKategori,
    namaKategori = namaKategori,
    deskripsiKategori = deskripsiKategori
)