package com.example.uaspam.ui.penulis.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Penulis
import com.example.uaspam.repository.PenulisRepository
import kotlinx.coroutines.launch

class InsertPenulisViewModel (private val penulis: PenulisRepository): ViewModel(){
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertPenulisState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertPenulis(){
        viewModelScope.launch {
            try {
                penulis.insertPenulis(uiState.insertUiEvent.toPenulis())
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
    val idPenulis: Int = 0 ,
    val namaPenulis: String = "",
    val biografi: String = "",
    val kontak : String =""
)

fun InsertUiEvent.toPenulis(): Penulis = Penulis(
    idPenulis = idPenulis,
    namaPenulis = namaPenulis,
    biografi = biografi,
    kontak = kontak
)

fun Penulis.toUiStatePenulis():InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Penulis.toInsertUiEvent():InsertUiEvent = InsertUiEvent(
    idPenulis = idPenulis,
    namaPenulis = namaPenulis,
    biografi = biografi,
    kontak = kontak
)