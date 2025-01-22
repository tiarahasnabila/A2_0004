package com.example.uaspam.ui.buku.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.uaspam.model.Buku
import com.example.uaspam.repository.BukuRepository
import com.example.uaspam.ui.buku.view.DetailDestinasi
import kotlinx.coroutines.launch
import okio.IOException

sealed class DetailUiState {
    data class Success(val buku: Buku) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailBukuViewModel (
    savedStateHandle: SavedStateHandle,
    private val bukuRepository: BukuRepository
) : ViewModel(){

    var bukuDetailState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    private val _id: String = checkNotNull(savedStateHandle[DetailDestinasi.NIM])

    init {
        getBukubyId()
    }

    fun getBukubyId() {
        viewModelScope.launch {
            bukuDetailState = DetailUiState.Loading
            bukuDetailState = try {
                val buku = bukuRepository.getBukubyId(_id)
                DetailUiState.Success(buku)
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }
}