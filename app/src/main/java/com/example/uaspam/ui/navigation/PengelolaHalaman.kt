package com.example.uaspam.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uaspam.ui.buku.view.DestinasiEntry
import com.example.uaspam.ui.buku.view.DestinasiHome
import com.example.uaspam.ui.buku.view.DetailDestinasi
import com.example.uaspam.ui.buku.view.DetailScreen
import com.example.uaspam.ui.buku.view.HomeBukuView
import com.example.uaspam.ui.buku.view.InsertBukuView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier,
    ){
        composable(DestinasiHome.route){
            HomeBukuView(
                navigateToltemEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = {
                }
            )
        }

        composable(DestinasiEntry.route) {
            InsertBukuView(
                navigateBack = {
                    navController.navigate(DestinasiHome.route){
                        popUpTo(DestinasiHome.route){
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}