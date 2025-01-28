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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateKategoriView(
    NavigateBack:() -> Unit,
    onNavigate:()-> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateKategoriViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    Scaffold (
        modifier = modifier,
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpKategori.titleRes,
                canNavigateBack = true,
                navigateUp = NavigateBack,
            )
        }
    ){ padding ->
        EntryBody(
            modifier = Modifier.padding(padding),
            onKategoriValueChange = viewModel::updateInsertKategoriState,
            insertUiState = viewModel.updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateData()
                    delay(600)
                    withContext(Dispatchers.Main) {
                        onNavigate()
                    }
                }
            }
        )
    }
}