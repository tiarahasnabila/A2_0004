package com.example.uaspam.ui.penulis.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspam.model.Penulis
import com.example.uaspam.ui.PenyediaViewModel
import com.example.uaspam.ui.navigation.CostumeTopAppBar
import com.example.uaspam.ui.navigation.DestinasiNavigasi
import com.example.uaspam.ui.penulis.viewmodel.DetailPenulisViewModel
import com.example.uaspam.ui.penulis.viewmodel.DetailUiState
import com.example.uaspam.ui.penulis.viewmodel.toPenulis


object DestinasiDetPenulis : DestinasiNavigasi {
    override val route = "detail"
    override val titleRes = "Detail Penerbit"
    const val IDPENULIS = "idPenerbit"
    val routeWithArgs = "$route/{$IDPENULIS}"
}

