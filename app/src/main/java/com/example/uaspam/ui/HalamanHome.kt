package com.example.uaspam.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspam.R
import com.example.uaspam.ui.ViewModel.HalamanHomeViewModel
import com.example.uaspam.ui.navigation.DestinasiNavigasi
import com.example.uaspam.ui.navigation.TopAppBarHome

object DestinasiHalamanHome : DestinasiNavigasi {
    override val route = "halamanhome"
    override val titleRes = "Halaman Utama"
}


@Composable
fun HalamanHome(
    viewModel: HalamanHomeViewModel = viewModel(),
    onNavigateToBuku: () -> Unit = {},
    onNavigateToKategori: () -> Unit = {},
    onNavigateToPenulis: () -> Unit ={},
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBarHome()
        },
        modifier = modifier
    )

    {

            innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Selamat Datang di Toko",
                    modifier = Modifier.padding(30.dp),
                    color = colorResource(id = R.color.purple_200),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                )

                Button(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp, end = 40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.purple_500)
                    ),
                    onClick = { viewModel.navigateToBuku() },

                    ) {
                    Text(

                        text = "Buku")
                }

                Button(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp, end = 40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.purple_500)),
                    onClick = { viewModel.navigateToKategori() }
                ) {
                    Text(text = "Kategori")
                }

                Button(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp, end = 40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.purple_500)),
                    onClick = { viewModel.navigateToPenulis() }
                ) {
                    Text(text = "Penulis")
                }

                Button(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp, end = 40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.purple_500)),
                    onClick = { viewModel.navigateToPenerbit() }
                ) {
                    Text(text = "Penerbit")
                }
            }
        }
    }



    LaunchedEffect(uiState.navigateToBuku) {
        if (uiState.navigateToBuku) {
            viewModel.resetNavigation()
            onNavigateToBuku()
        }
    }

    LaunchedEffect(uiState.navigateToKategori) {
        if (uiState.navigateToKategori) {
            viewModel.resetNavigation()
            onNavigateToKategori()
        }
    }

    LaunchedEffect(uiState.navigateToPenulis) {
        if (uiState.navigateToPenulis) {
            viewModel.resetNavigation()
            onNavigateToPenulis()
        }
    }
}