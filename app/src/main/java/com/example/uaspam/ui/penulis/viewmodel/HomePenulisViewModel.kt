package com.example.uaspam.ui.penulis.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.uaspam.model.Penulis
import com.example.uaspam.repository.PenulisRepository
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomeuiState{
    data class Success(val penulis: List<Penulis>): HomeuiState()
    object Error: HomeuiState()
    object Loading: HomeuiState()
}

