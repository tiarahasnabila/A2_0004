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

