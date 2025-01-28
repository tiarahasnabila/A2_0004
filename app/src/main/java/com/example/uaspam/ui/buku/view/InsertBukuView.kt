package com.example.uaspam.ui.buku.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspam.model.Kategori
import com.example.uaspam.ui.buku.viewmodel.InsertBukuViewModel
import com.example.uaspam.ui.buku.viewmodel.InsertUiEvent
import com.example.uaspam.ui.buku.viewmodel.InsertUiState
import com.example.uaspam.ui.PenyediaViewModel
import com.example.uaspam.ui.navigation.CostumeTopAppBar
import com.example.uaspam.ui.navigation.DestinasiNavigasi
import kotlinx.coroutines.launch

object DestinasiEntry: DestinasiNavigasi {
    override val route = "item_entry"
    override val titleRes = "Tambah Buku"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertBukuView(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertBukuViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiEntry.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ){ innerPadding ->
        EntryBody(
            insertUiState = viewModel.uiState,
            onBukuValueChange = viewModel::updateInsertBukuState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertBuku()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryBody(
    insertUiState: InsertUiState,
    onBukuValueChange: (InsertUiEvent) -> Unit,
    onSaveClick:() -> Unit,
    modifier: Modifier = Modifier
){
    Column (
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ){
        FormInput(
            insertUiEvent = insertUiState.insertUiEvent,
            onValueChange = onBukuValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button (
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(text = "Simpan")
        }
    }
}

@Composable
fun FormInput(
    insertUiEvent: InsertUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertUiEvent.idBuku.toString(), // Konversi Int ke String untuk ditampilkan
            onValueChange = { input ->
                val newValue = input.toIntOrNull() // Konversi String ke Int
                if (newValue != null) {
                    onValueChange(insertUiEvent.copy(idKategori = newValue))
                }
            },
            label = { Text("Id Buku") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.namaBuku,
            onValueChange = { onValueChange(insertUiEvent.copy(namaBuku = it)) },
            label = { Text("Nama Buku") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.deskripsiBuku,
            onValueChange = { onValueChange(insertUiEvent.copy(deskripsiBuku = it)) },
            label = { Text("Deskripsi Buku") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.tanggalTerbit,
            onValueChange = { onValueChange(insertUiEvent.copy(tanggalTerbit = it)) },
            label = { Text("Tanggal Terbit") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.statusBuku,
            onValueChange = { onValueChange(insertUiEvent.copy(statusBuku = it)) },
            label = { Text("Status Buku") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.idKategori.toString(), // Konversi Int ke String untuk ditampilkan
            onValueChange = { input ->
                val newValue = input.toIntOrNull() // Konversi String ke Int
                if (newValue != null) {
                    onValueChange(insertUiEvent.copy(idKategori = newValue))
                }
            },
            label = { Text("Id Kategori") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = insertUiEvent.idPenulis.toString(), // Konversi Int ke String untuk ditampilkan
            onValueChange = { input ->
                val newValue = input.toIntOrNull() // Konversi String ke Int
                if (newValue != null) {
                    onValueChange(insertUiEvent.copy(idPenulis = newValue))
                }
            },
            label = { Text(" Id Penulis") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = insertUiEvent.idPenerbit.toString(), // Konversi Int ke String untuk ditampilkan
            onValueChange = { input ->
                val newValue = input.toIntOrNull() // Konversi String ke Int
                if (newValue != null) {
                    onValueChange(insertUiEvent.copy(idPenerbit = newValue))
                }
            },
            label = { Text("Id Penerbit") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        if (enabled) {
            Text(
                text = "Isi Semua Data!",
                modifier = Modifier.padding(12.dp)
            )
        }
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}