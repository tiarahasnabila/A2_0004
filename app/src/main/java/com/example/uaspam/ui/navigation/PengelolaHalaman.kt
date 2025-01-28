package com.example.uaspam.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uaspam.ui.HalamanHome
import com.example.uaspam.ui.buku.view.DestinasiDetail
import com.example.uaspam.ui.buku.view.DestinasiEntryBuku
import com.example.uaspam.ui.buku.view.DestinasiUpdate
import com.example.uaspam.ui.buku.view.DetailView
import com.example.uaspam.ui.buku.view.HomeBukuView
import com.example.uaspam.ui.buku.view.InsertBukuView
import com.example.uaspam.ui.buku.view.UpdateScreen
import com.example.uaspam.ui.kategori.view.DestinasiDKategori
import com.example.uaspam.ui.kategori.view.DestinasiEtKategori
import com.example.uaspam.ui.kategori.view.DestinasiUpKategori
import com.example.uaspam.ui.kategori.view.DetailKategoriView
import com.example.uaspam.ui.kategori.view.HomeKategoriView
import com.example.uaspam.ui.kategori.view.InsertKategoriView
import com.example.uaspam.ui.kategori.view.UpdateKategoriView
import com.example.uaspam.ui.penulis.view.DestinasiDetPenulis
import com.example.uaspam.ui.penulis.view.DestinasiPenulis
import com.example.uaspam.ui.penulis.view.HomePenulisView
import com.example.uaspam.ui.penulis.view.InsertPenulisView

@Composable
fun PengelolaHalaman(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = DestinasiHalamanHome.route,
        modifier = Modifier,
    ) {

        composable(DestinasiHalamanHome.route){
            HalamanHome(
                onNavigateToBuku = {
                    navController.navigate(DestinasiHome.route)
                },
                onNavigateToKategori = {
                    navController.navigate(DestinasiKategori.route)
                }
            )
        }
        composable(DestinasiHome.route)
        {
            HomeBukuView(
                onBackClick = {navController.navigate(DestinasiHalamanHome.route)},
                navigateToItemEntry = {navController.navigate(DestinasiEntryBuku.route)},
                onDetailClick = { idBuku ->
                    navController.navigate("${DestinasiDetail.route}/$idBuku")
                }
            )
        }
        composable(DestinasiEntryBuku.route) {
            InsertBukuView(
                navigateBack = {
                    navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiHome.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            DestinasiDetail.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetail.IDBUKU) {
                    type = NavType.StringType })
        ) { backStackEntry ->
            val idBuku = backStackEntry.arguments?.getString(DestinasiDetail.IDBUKU) ?: ""
            DetailView(
                navigateBack = { navController.navigateUp() },
                onEditClick = { navController.navigate("update") },
                onDeleteClick = { navController.navigateUp() }
//                idBuku = idBuku // Pass the idBuku to the Detail View
            )
        }

        composable(
            DestinasiUpdate.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiUpdate.IDBUKU) {
                    type = NavType.StringType
                }
            )
        ) {
            UpdateScreen(
                NavigateBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
            )
        }

        // tabel kategori

        composable(DestinasiKategori.route)
        {
            HomeKategoriView(
//                onBackClick = {navController.navigate(DestinasiHalamanHome.route)},
                navigateToItemEntry = {navController.navigate(DestinasiEtKategori.route)},
                onDetailClick = { idKategori ->
                    navController.navigate("${DestinasiDKategori.route}/$idKategori")
                }
            )
        }

        composable(DestinasiEtKategori.route) {
            InsertKategoriView(
                navigateBack = {
                    navController.navigate(DestinasiKategori.route) {
                        popUpTo(DestinasiKategori.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            DestinasiDKategori.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDKategori.IDKATEGORI) {
                    type = NavType.StringType })
        ) { backStackEntry ->
            val idKategori = backStackEntry.arguments?.getString(DestinasiDKategori.IDKATEGORI) ?: ""
            DetailKategoriView(
                navigateBack = { navController.navigateUp() },
                onEditClick = { navController.navigate("update") },
                onDeleteClick = { navController.navigateUp() }
//                idKategori = idKategori // Pass the idBuku to the Detail View
            )
        }

        composable(
            DestinasiUpKategori.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiUpKategori.IDKATEGORI) {
                    type = NavType.StringType
                }
            )
        ) {
            UpdateKategoriView(
                NavigateBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
            )
        }

        // tabel penulis
//
//        composable(DestinasiPenulis.route)
//        {
//            HomePenulisView(
//                onBackClick = {navController.navigate(DestinasiHalamanHome.route)},
//                navigateToItemEntry = {navController.navigate(DestinasiEntry.route)},
//                onDetailClick = { idPenulis ->
//                    navController.navigate("${DestinasiDetPenulis.route}/$idPenulis")
//                }
//            )
//        }
//
//        composable(DestinasiEntry.route) {
//            InsertPenulisView(
//                navigateBack = {
//                    navController.navigate(DestinasiPenulis.route) {
//                        popUpTo(DestinasiPenulis.route) {
//                            inclusive = true
//                        }
//                    }
//                }
//            )
//        }
    }
}