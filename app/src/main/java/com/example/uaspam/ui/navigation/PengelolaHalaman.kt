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
import com.example.uaspam.ui.buku.view.DestinasiEntry
import com.example.uaspam.ui.buku.view.DestinasiUpdate
import com.example.uaspam.ui.buku.view.DetailView
import com.example.uaspam.ui.buku.view.HomeBukuView
import com.example.uaspam.ui.buku.view.InsertBukuView
import com.example.uaspam.ui.buku.view.UpdateScreen
import com.example.uaspam.ui.kategori.view.HomeKategoriView

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
                    navController.navigate(DestinasiHome.route)
                }
            )
        }
        composable(DestinasiHome.route)
        {
            HomeBukuView(
                onBackClick = {navController.navigate(DestinasiHalamanHome.route)},
                navigateToItemEntry = {navController.navigate(DestinasiEntry.route)},
                onDetailClick = { idBuku ->
                    navController.navigate("${DestinasiDetail.route}/$idBuku")
                }
            )
        }
        composable(DestinasiEntry.route) {
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

//        composable(DestinasiHome.route)
//        {
//            HomeKategoriView(
////                onBackClick = {navController.navigate(DestinasiHalamanHome.route)},
//                navigateToItemEntry = {navController.navigate(DestinasiEntry.route)},
//                onDetailClick = { idKategori ->
//                    navController.navigate("${DestinasiDetail.route}/$idKategori")
//                }
//            )
//        }
    }
}