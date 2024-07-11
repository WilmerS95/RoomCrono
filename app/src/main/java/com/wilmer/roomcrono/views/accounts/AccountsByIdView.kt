package com.wilmer.roomcrono.views.accounts

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.wilmer.roomcrono.components.accounts.CardAccount
import com.wilmer.roomcrono.components.crono.MainTitle
import com.wilmer.roomcrono.view_models.accounts.AccountsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AccountsByIdView(viewModel: AccountsViewModel, navController: NavController) {
    val accounts by viewModel.accountsById.collectAsState(initial = emptyList())
    val nombre = if (accounts.isNotEmpty()) accounts.first().usuario.nombre else "Usuario"

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                title = {
                    MainTitle(title = "--- Cuentas de ${nombre} ---")
                },
                actions = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) {
        Column (
            modifier = Modifier.padding(it)
        ) {
            ContentAccountsViewById(viewModel = viewModel)
        }

    }
}

@Composable
fun ContentAccountsViewById(
    viewModel: AccountsViewModel,
) {
    val accounts by viewModel.accountsById.collectAsState(initial = emptyList())
    LazyColumn(

        modifier = Modifier.fillMaxSize()
            .background(color = Color.White)
    ) {
        items(accounts) { account ->
            CardAccount(
                accountsModel = account,

            )
        }
    }
}
