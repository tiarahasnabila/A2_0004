package com.example.uaspam.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.uaspam.ui.PenyediaViewModel.aplikasiKontak
import com.example.uaspam.ui.buku.viewmodel.DetailBukuViewModel
import com.example.uaspam.ui.buku.viewmodel.HomeBukuViewModel
import com.example.uaspam.ui.buku.viewmodel.InsertBukuViewModel
import com.example.uaspam.ui.buku.viewmodel.UpdateBukuViewModel
import com.example.uaspam.ui.kategori.viewmodel.DetailKategoriViewModel
import com.example.uaspam.ui.kategori.viewmodel.HomeKategoriViewModel
import com.example.uaspam.ui.kategori.viewmodel.InsertKategoriViewModel
import com.example.uaspam.ui.kategori.viewmodel.UpdateKategoriViewModel
import com.example.uaspam.ui.penerbit.viewmodel.DetailPenerbitViewModel
import com.example.uaspam.ui.penerbit.viewmodel.HomePenerbitViewModel
import com.example.uaspam.ui.penerbit.viewmodel.InsertPenerbitViwModel
import com.example.uaspam.ui.penerbit.viewmodel.UpdatePenerbitViewModel
import com.example.uaspam.ui.penulis.viewmodel.DetailPenulisViewModel
import com.example.uaspam.ui.penulis.viewmodel.HomePenulisViewModel
import com.example.uaspam.ui.penulis.viewmodel.InsertPenulisViewModel
import com.example.uaspam.ui.penulis.viewmodel.UpdatePenulisViewModel


object PenyediaViewModel {
    val Factory = viewModelFactory {

        // tabel buku

        initializer {
            InsertBukuViewModel(aplikasiKontak().container.bukuRepository
            )
        }

        initializer {
            HomeBukuViewModel(aplikasiKontak().container.bukuRepository
            )
        }

        initializer {
            UpdateBukuViewModel(createSavedStateHandle(), aplikasiKontak().container.bukuRepository
            )
        }

        initializer {
            DetailBukuViewModel(createSavedStateHandle(),aplikasiKontak().container.bukuRepository
            )
        }

        // tabel kategori
        initializer {
            InsertKategoriViewModel(aplikasiKontak().container.kategoriRepository
            )
        }

        initializer {
            HomeKategoriViewModel(aplikasiKontak().container.kategoriRepository
            )
        }

        initializer {
            UpdateKategoriViewModel(createSavedStateHandle(),aplikasiKontak().container.kategoriRepository
            )
        }

        initializer {
            DetailKategoriViewModel(createSavedStateHandle(),aplikasiKontak().container.kategoriRepository
            )
        }

        // tabel penerbit
        initializer {
            InsertPenerbitViwModel(aplikasiKontak().container.penerbitRepository
            )
        }

        initializer {
            HomePenerbitViewModel(aplikasiKontak().container.penerbitRepository
            )
        }

        initializer {
            UpdatePenerbitViewModel(createSavedStateHandle(),aplikasiKontak().container.penerbitRepository
            )
        }

        initializer {
            DetailPenerbitViewModel(createSavedStateHandle(),aplikasiKontak().container.penerbitRepository
            )
        }

        // tabel penulis
        initializer {
            InsertPenulisViewModel(aplikasiKontak().container.penulisRepository
            )
        }

        initializer {
            HomePenulisViewModel(aplikasiKontak().container.penulisRepository
            )
        }

        initializer {
            UpdatePenulisViewModel(createSavedStateHandle(),aplikasiKontak().container.penulisRepository
            )
        }

        initializer {
            DetailPenulisViewModel(createSavedStateHandle(),aplikasiKontak().container.penulisRepository
            )
        }
    }


    fun CreationExtras.aplikasiKontak(): BukuApplications =
        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BukuApplications)
}