package com.example.uaspam.ui.penerbit.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Penerbit
import com.example.uaspam.repository.PenerbitRepository
import com.example.uaspam.ui.penerbit.view.DestinasiDetail
import kotlinx.coroutines.launch

class DetailPenerbitViewModel(
    savedStateHandle: SavedStateHandle,
    private val penerbitRepository: PenerbitRepository
) : ViewModel() {
    private val idPenerbit: String = checkNotNull(savedStateHandle[DestinasiDetail.IDPENERBIT.toString()])

    var detailUiState: DetailUiState by mutableStateOf(DetailUiState())
        private set

    init {
        getPenerbitbyId()
    }

    private fun getPenerbitbyId() {
        viewModelScope.launch {
            detailUiState = DetailUiState(isLoading = true)
            try {
                val result = penerbitRepository.getPenerbitbyId(idPenerbit)
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

    fun deletePenerbit() {
        viewModelScope.launch {
            detailUiState = DetailUiState(isLoading = true)
            try {
                penerbitRepository.deletePenerbit(idPenerbit)

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

fun Penerbit.toDetailUiEvent(): InsertUiEvent {
    return InsertUiEvent(
        idPenerbit = idPenerbit,
        namaPenerbit = namaPenerbit,
        alamatPenerbit = alamatPenerbit
    )
}

data class PenerbitUiEvent(
    val idPenerbit : String = "",
    val namaPenerbit : String = "",
    val alamatPenerbit : String = "",
    val teleponPenerbit : String = ""

)