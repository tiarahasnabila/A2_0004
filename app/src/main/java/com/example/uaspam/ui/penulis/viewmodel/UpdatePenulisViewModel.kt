package com.example.uaspam.ui.penulis.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.repository.PenulisRepository
import com.example.uaspam.ui.penulis.view.DestinasiUpdate
import kotlinx.coroutines.launch


        fun updateInsertPenulisState(insertUiEvent: InsertUiEvent) {
            updateUiState = updateUiState.copy(insertUiEvent = insertUiEvent)
        }

        fun updateData() {
            viewModelScope.launch {
                try {
                    penulisRepository.updatePenulis(_idPenulis, updateUiState.insertUiEvent.toPenulis())
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
