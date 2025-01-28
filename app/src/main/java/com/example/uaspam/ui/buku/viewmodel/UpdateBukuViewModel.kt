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



    fun updateInsertBukuState(insertUiEvent: InsertUiEvent){
        updateUiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun updateData(){
        viewModelScope.launch {
            try {
                bukuRepository.updateBuku(_idBuku , updateUiState.insertUiEvent.toBuku())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}