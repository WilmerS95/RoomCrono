package com.wilmer.roomcrono.views.accounts

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.wilmer.roomcrono.components.accounts.CardAccount
import com.wilmer.roomcrono.view_models.accounts.AccountsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AccountsView(viewModel: AccountsViewModel, navController: NavController) {
    Scaffold{
        ContentAccountsView(viewModel = viewModel, navController = navController)
    }
}

@Composable
fun ContentAccountsView(
    viewModel: AccountsViewModel,
    navController: NavController
) {
    val accounts by viewModel.accounts.collectAsState(initial = emptyList())

    LazyColumn(

        modifier = Modifier.fillMaxSize()
            .background(color = Color.White)
    ) {
        items(accounts) { account ->
            CardAccount(
                accountsModel = account,
                onClick = {
                    viewModel.getAccountById(account.usuario.id)
                    navController.navigate("AccountsById/${account.usuario.id}")
                }
            )
        }
    }
}