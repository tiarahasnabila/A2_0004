package com.example.uaspam.ui.penulis.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspam.ui.PenyediaViewModel
import com.example.uaspam.ui.navigation.CostumeTopAppBar
import com.example.uaspam.ui.navigation.DestinasiNavigasi
import com.example.uaspam.ui.penulis.viewmodel.UpdatePenulisViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpdate : DestinasiNavigasi {
    override val route = "update"
    override val titleRes = "Update Penulis"
    const val IDPENULIS = "idPenulis"
    val routeWithArgs = "$route/{$IDPENULIS}"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePenulisView(
    NavigateBack:() -> Unit,
    onNavigate:()-> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdatePenulisViewModel = viewModel(factory = PenyediaViewModel.Factory)
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
            onPenulisValueChange = viewModel::updateInsertPenulisState,
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