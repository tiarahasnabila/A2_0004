package com.example.uaspam.ui.penerbit.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspam.R
import com.example.uaspam.model.Penerbit
import com.example.uaspam.ui.PenyediaViewModel
import com.example.uaspam.ui.navigation.CostumeTopAppBar
import com.example.uaspam.ui.navigation.DestinasiNavigasi
import com.example.uaspam.ui.penerbit.viewmodel.HomePenerbitViewModel
import com.example.uaspam.ui.penerbit.viewmodel.HomeuiState

object DestinasiHome : DestinasiNavigasi {
    override val route = "penerbit"
    override val titleRes = "Home Penerbit"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePenerbitView(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit,
    viewModel: HomePenerbitViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    var showDeleteDialog by remember { mutableStateOf(false) }
    var selectedPenerbit: Penerbit? by remember { mutableStateOf(null) }

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHome.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getPenerbit()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            )
            {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Penerbit" )
            }
        },
    ){innerPadding ->
        HomeStatus(
            homeuiState = viewModel.penerbitUiState,
            retryAction = { viewModel.getPenerbit()},
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = { penerbit ->
                selectedPenerbit = penerbit
                showDeleteDialog = true
            }
        )

        if (showDeleteDialog && selectedPenerbit != null) {
            DeleteConfirmationDialog(
                onDeleteConfirm = {
                    selectedPenerbit?.let { penerbit ->
                        viewModel.deletePenerbit(penerbit.idPenerbit.toString())
                    }
                    showDeleteDialog = false
                },
                onDeleteCancel = {
                    showDeleteDialog = false
                }
            )
        }
    }
}

@Composable
fun HomeStatus(
    homeuiState: HomeuiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Penerbit) -> Unit = {},
    onDetailClick: (String) -> Unit
){
    when (homeuiState){
        is HomeuiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is HomeuiState.Success ->
            if (homeuiState.penerbit.isEmpty()){
                return Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text(text = "Tidak ada data Penerbit")
                }
            }else {
                PenerbitLayout(
                    penerbit = homeuiState.penerbit,
                    modifier=modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.toString())
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        is HomeuiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun OnLoading(modifier: Modifier = Modifier){
    Image(
        modifier = modifier
            .size(100.dp)
            .padding(40.dp),
        painter = painterResource(R.drawable.gambar),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = painterResource(id = R.drawable.gambarlagi), contentDescription = ""
        )
        Text(text = stringResource(id = R.string.loading_failed),
            modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun PenerbitLayout(
    penerbit: List<Penerbit>,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit,
    onDeleteClick: (Penerbit) -> Unit = {}
){
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(penerbit) { penerbit ->
            PenerbitCard(
                penerbit = penerbit,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(penerbit.idPenerbit.toString()) },
                onDeleteClick = {
                    onDeleteClick(penerbit)
                }
            )
        }
    }
}

@Composable
fun PenerbitCard(
    penerbit: Penerbit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Penerbit) -> Unit = {}
){
    Card (
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ){
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = penerbit.idPenerbit.toString(),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(penerbit) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                    )
                }
                Text(
                    text = penerbit.namaPenerbit,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = penerbit.alamatPenerbit,
                style = MaterialTheme.typography.titleMedium
            )
        }
        Text(
            text = penerbit.teleponPenerbit,
            style = MaterialTheme.typography.titleMedium
        )
    }
    }


@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
){
    AlertDialog(onDismissRequest = {/*Do Nothing*/},
        title = { Text("Delete Data") },
        text = { Text("Apakah anda yakin ingin menghapus data ini?") },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text("Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text("Yes")
            }
        }
    )
}