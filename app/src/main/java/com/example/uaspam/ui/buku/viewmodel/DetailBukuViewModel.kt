package com.example.uaspam.ui.buku.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Buku
import com.example.uaspam.repository.BukuRepository
import com.example.uaspam.ui.buku.view.DestinasiDetail
import kotlinx.coroutines.launch

class DetailBukuViewModel(
    savedStateHandle: SavedStateHandle,
    private val bukuRepository: BukuRepository
) : ViewModel() {
    private val idBuku: String = checkNotNull(savedStateHandle[DestinasiDetail.IDBUKU])

    var detailUiState: DetailUiState by mutableStateOf(DetailUiState())
        private set

    init {
        getBukubyId()
    }

    private fun getBukubyId() {
        viewModelScope.launch {
            detailUiState = DetailUiState(isLoading = true)
            try {
                val result = bukuRepository.getBukubyId(idBuku)
                detailUiState = DetailUiState(
                    detailUiEvent = result.toDetailUiEvent(),
                    isLoading = false
                )
            } catch (e: Exception) {
                detailUiState = DetailUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown"
                )
            }
        }
    }

    fun deleteBuku() {
        viewModelScope.launch {
            detailUiState = DetailUiState(isLoading = true)
            try {
                bukuRepository.deleteBuku(idBuku)

                detailUiState = DetailUiState(isLoading = false)
            } catch (e: Exception) {
                detailUiState = DetailUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown Error"
                )
            }
        }
    }
}


data class DetailUiState(
    val detailUiEvent: InsertUiEvent = InsertUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == InsertUiEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != InsertUiEvent()
}

fun Buku.toDetailUiEvent(): InsertUiEvent{
    return InsertUiEvent(
        idBuku = idBuku,
        namaBuku = namaBuku,
        deskripsiBuku = deskripsiBuku ,
        tanggalTerbit = tanggalTerbit,
        statusBuku = statusBuku,
        idPenulis = idPenulis,
        idKategori = idKategori
    )
}
