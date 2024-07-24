package com.wilmer.roomcrono.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.wilmer.roomcrono.view_models.accounts.AccountsViewModel
import com.wilmer.roomcrono.view_models.crono.CronometroViewModel
import com.wilmer.roomcrono.view_models.crono.CronosViewModel
import com.wilmer.roomcrono.views.accounts.AccountsByIdView
import com.wilmer.roomcrono.views.crono.AddView
import com.wilmer.roomcrono.views.crono.EditView
import com.wilmer.roomcrono.views.crono.HomeView

@Composable
fun NavManager(cronometroViewModel: CronometroViewModel, cronosViewModel: CronosViewModel, accountsViewModel: AccountsViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") {
            HomeView(navController, cronosViewModel, accountsViewModel)
        }
        composable ("AddView"){
            AddView(navController, cronometroViewModel, cronosViewModel)
        }
        composable ("EditView/{id}", arguments = listOf(
            navArgument("id"){type = NavType.LongType}
        )){
            val id = it.arguments?.getLong("id") ?: 0
            EditView(navController, cronometroViewModel, cronosViewModel, id)
        }
        composable("AccountsById/{id}", arguments = listOf(
            navArgument("id"){type = NavType.LongType}
        )){
            AccountsByIdView(accountsViewModel)
        }
        /*composable("AccountsView"){
            AccountsView(viewModel, navController)
        }*/
    }
}