package com.example.uaspam.ui.buku.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.uaspam.model.Buku
import com.example.uaspam.repository.BukuRepository
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomeuiState{
    data class Success(
        val buku: List<Buku>
    ): HomeuiState()
    object Error: HomeuiState()
    object Loading: HomeuiState()
}

class HomeBukuViewModel(
    private val buku: BukuRepository
): ViewModel(){
    var bukuUiState: HomeuiState by mutableStateOf(HomeuiState.Loading)
        private set

    init {
        getBuku()
    }

    fun getBuku(){
        viewModelScope.launch {
            bukuUiState = HomeuiState.Loading
            bukuUiState = try {
                HomeuiState.Success(buku.getBuku())
            } catch (e: IOException){
                HomeuiState.Error
            } catch (e: HttpException){
                HomeuiState.Error
            }
        }
    }

    fun deleteBuku(id: String){
        viewModelScope.launch {
            try {
                buku.deleteBuku(id)
            } catch (e: IOException){
                HomeuiState.Error
            } catch (e: HttpException){
                HomeuiState.Error
            }
        }
    }
}