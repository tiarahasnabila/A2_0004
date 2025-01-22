package com.example.uaspam.ui.buku.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.repository.BukuRepository
import com.example.uaspam.ui.buku.view.DestinasiUpdate
import kotlinx.coroutines.launch

class UpdateBukuViewModel(
    savedStateHandle: SavedStateHandle,
    private val bukuRepository:BukuRepository
): ViewModel(){
    var updateUiState by mutableStateOf(InsertUiState())
        private set

    private val _nim: String = checkNotNull(savedStateHandle[DestinasiUpdate.NIM])

    init {
        viewModelScope.launch {
            updateUiState = bukuRepository.getBukubyId(_nim)
                .toUiStateBuku()
        }
    }

    fun updateInsertMhsState(insertUiEvent: InsertUiEvent){
        updateUiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun updateBuku(){
        viewModelScope.launch {
            try {
                bukuRepository.updateBuku(id = "", updateUiState.insertUiEvent.toBuku())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}