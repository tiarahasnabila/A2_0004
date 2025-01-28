package com.example.uaspam.ui.kategori.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspam.ui.PenyediaViewModel
import com.example.uaspam.ui.navigation.CostumeTopAppBar
import com.example.uaspam.ui.kategori.viewmodel.UpdateKategoriViewModel
import com.example.uaspam.ui.navigation.DestinasiNavigasi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpKategori : DestinasiNavigasi {
    override val route = "update"
    override val titleRes = "Update Kategori"
    const val IDKATEGORI = "idKategori"
    val routeWithArgs = "$route/{$IDKATEGORI}"
}
