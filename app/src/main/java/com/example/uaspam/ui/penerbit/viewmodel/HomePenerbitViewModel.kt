package com.example.uaspam.ui.penerbit.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.uaspam.model.Penerbit
import com.example.uaspam.repository.PenerbitRepository
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomeuiState{
    data class Success(val penerbit: List<Penerbit>): HomeuiState()
    object Error: HomeuiState()
    object Loading: HomeuiState()
}

class HomePenerbitViewModel(private val penerbit: PenerbitRepository): ViewModel(){
    var penerbitUiState: HomeuiState by mutableStateOf(HomeuiState.Loading)
        private set

    init {
        getPenerbit()
    }

    fun getPenerbit(){
        viewModelScope.launch {
            penerbitUiState = HomeuiState.Loading
            penerbitUiState = try {
                HomeuiState.Success(penerbit.getPenerbit())
            } catch (e: IOException){
                HomeuiState.Error
            } catch (e: HttpException){
                HomeuiState.Error
            }
        }
    }

    fun deletePenerbit(id: String){
        viewModelScope.launch {
            try {
                penerbit.deletePenerbit(id)
            } catch (e: IOException){
                HomeuiState.Error
            } catch (e: HttpException){
                HomeuiState.Error
            }
        }
    }
}