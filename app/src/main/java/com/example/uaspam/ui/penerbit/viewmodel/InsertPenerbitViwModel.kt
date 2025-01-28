package com.example.uaspam.ui.penerbit.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Penerbit
import com.example.uaspam.repository.PenerbitRepository
import kotlinx.coroutines.launch

class InsertPenerbitViwModel (private val penerbit: PenerbitRepository): ViewModel(){
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertPenerbitState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertPenerbit(){
        viewModelScope.launch {
            try {
                penerbit.insertPenerbit(uiState.insertUiEvent.toPenerbit())
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
    val idPenerbit: Int = 0,
    val namaPenerbit: String = "",
    val alamatPenerbit: String = "",
    val teleponPenerbit : String =""
)

fun InsertUiEvent.toPenerbit(): Penerbit = Penerbit(
    idPenerbit = idPenerbit,
    namaPenerbit = namaPenerbit,
    alamatPenerbit = alamatPenerbit,
    teleponPenerbit = teleponPenerbit
)

fun Penerbit.toUiStatePenerbit():InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Penerbit.toInsertUiEvent():InsertUiEvent = InsertUiEvent(
    idPenerbit = idPenerbit,
    namaPenerbit = namaPenerbit,
    alamatPenerbit = alamatPenerbit,
    teleponPenerbit = teleponPenerbit
)