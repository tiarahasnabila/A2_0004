package com.example.uaspam.ui.penerbit.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.repository.PenerbitRepository
import com.example.uaspam.ui.penerbit.view.DestinasiUpdate
import kotlinx.coroutines.launch

class UpdatePenerbitViewModel (
    savedStateHandle: SavedStateHandle,
    private val penerbitRepository: PenerbitRepository)
    : ViewModel() {

        var updateUiState by mutableStateOf(InsertUiState())
        private set

                private val _idPenerbit: String = checkNotNull(savedStateHandle[DestinasiUpdate.idPenerbit])

        init {
            viewModelScope.launch {
                try {
                    updateUiState = penerbitRepository.getPenerbitbyId(_idPenerbit)
                        .toUiStatePenerbit()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }


