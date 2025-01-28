package com.example.uaspam.ui.buku.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspam.ui.PenyediaViewModel
import com.example.uaspam.ui.buku.viewmodel.UpdateBukuViewModel
import com.example.uaspam.ui.navigation.CostumeTopAppBar
import com.example.uaspam.ui.navigation.DestinasiNavigasi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpdate : DestinasiNavigasi {
    override val route = "update"
    override val titleRes = "Update Buku"
    const val IDBUKU = "idBuku"
    val routeWithArgs = "$route/{$IDBUKU}"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScreen(
    NavigateBack:() -> Unit,
    onNavigate:()-> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateBukuViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    Scaffold (
        modifier = modifier,
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdate.titleRes,
                canNavigateBack = true,
                navigateUp = NavigateBack,
            )
        }
    ){ padding ->
        EntryBody(
            modifier = Modifier.padding(padding),
            onBukuValueChange = viewModel::updateInsertBukuState,
            insertUiState = viewModel.updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateData()
                    delay(600)
                    withContext(Dispatchers.Main){
                        onNavigate()
                    }
                }
            }
        )
    }
}