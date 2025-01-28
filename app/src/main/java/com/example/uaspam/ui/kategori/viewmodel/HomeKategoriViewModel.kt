package com.example.uaspam.ui.kategori.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.uaspam.model.Kategori
import com.example.uaspam.repository.KategoriRepository
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomeuiState{
    data class Success(val kategori: List<Kategori>): HomeuiState()
    object Error: HomeuiState()
    object Loading: HomeuiState()
}

class HomeKategoriViewModel(private val kategori: KategoriRepository): ViewModel(){
    var kategoriUiState: HomeuiState by mutableStateOf(HomeuiState.Loading)
        private set

    init {
        getKategori()
    }

    fun getKategori(){
        viewModelScope.launch {
            kategoriUiState = HomeuiState.Loading
            kategoriUiState = try {
                HomeuiState.Success(kategori.getKategori())
            } catch (e: IOException){
                HomeuiState.Error
            } catch (e: HttpException){
                HomeuiState.Error
            }
        }
    }

