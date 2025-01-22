package com.example.uaspam.ui.buku.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.uaspam.BukuApplications

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            InsertBukuViewModel(
                aplikasiKontak().container.bukuRepository
            )
        }

        initializer {
            HomeBukuViewModel(
                aplikasiKontak().container.bukuRepository
            )
        }
    }

    fun CreationExtras.aplikasiKontak(): BukuApplications =
        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BukuApplications)
}