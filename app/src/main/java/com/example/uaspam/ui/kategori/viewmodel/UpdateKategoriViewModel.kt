package com.example.uaspam.ui.kategori.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.repository.KategoriRepository
import com.example.uaspam.ui.kategori.view.DestinasiUpKategori
import kotlinx.coroutines.launch

class UpdateKategoriViewModel (
    savedStateHandle: SavedStateHandle,
    private val kategoriRepository: KategoriRepository)
    : ViewModel() {

        var updateUiState by mutableStateOf(InsertUiState())
        private set

                private val _idKategori: String = checkNotNull(savedStateHandle[DestinasiUpKategori.IDKATEGORI])

        init {
            viewModelScope.launch {
                try {
                    updateUiState = kategoriRepository.getKategoribyId(_idKategori)
                        .toUiStateKategori()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        fun updateInsertKategoriState(insertUiEvent: InsertUiEvent) {
            updateUiState = updateUiState.copy(insertUiEvent = insertUiEvent)
        }

        fun updateData() {
            viewModelScope.launch {
                try {
                    kategoriRepository.updateKategori(_idKategori, updateUiState.insertUiEvent.toKategori())
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }