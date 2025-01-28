package com.example.uaspam.ui.ViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HalamanHomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HalamanHomeUiState())
    val uiState: StateFlow<HalamanHomeUiState> = _uiState

    fun navigateToBuku() {
        _uiState.update { it.copy(navigateToBuku = true, navigateToKategori = false) }
    }

    fun navigateToKategori() {
        _uiState.update { it.copy(navigateToBuku = false, navigateToKategori = true) }
    }

    fun navigateToPenulis() {
        _uiState.update { it.copy(navigateToBuku = false , navigateToPenulis = true) }
    }

    fun navigateToPenerbit() {
        _uiState.update { it.copy(navigateToBuku = false , navigateToPenerbit = true) }
    }

    fun resetNavigation() {
        _uiState.update { it.copy(navigateToBuku = false, navigateToKategori  = false) }
    }
}

data class HalamanHomeUiState(
    val navigateToBuku: Boolean = false,
    val navigateToKategori: Boolean = false,
    val navigateToPenulis: Boolean = false,
    val navigateToPenerbit: Boolean = false
)
