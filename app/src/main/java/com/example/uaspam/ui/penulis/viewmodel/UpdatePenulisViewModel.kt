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

class UpdatePenulisViewModel (
    savedStateHandle: SavedStateHandle,
    private val penulisRepository: PenulisRepository)
    : ViewModel() {

        var updateUiState by mutableStateOf(InsertUiState())
        private set

                private val _idPenulis: String = checkNotNull(savedStateHandle[DestinasiUpdate.IDPENULIS])

        init {
            viewModelScope.launch {
                try {
                    updateUiState = penulisRepository.getPenulisbyId(_idPenulis)
                        .toUiStatePenulis()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

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
