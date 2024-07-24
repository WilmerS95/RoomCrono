package com.wilmer.roomcrono.views.accounts

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.wilmer.roomcrono.components.accounts.CardAccount
import com.wilmer.roomcrono.view_models.accounts.AccountsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AccountsByIdView(viewModel: AccountsViewModel) {
    val accounts by viewModel.accountsById.collectAsState(initial = emptyList())
    val nombre = if (accounts.isNotEmpty()) accounts.first().usuario.nombre else "Usuario"

    LaunchedEffect(Unit) {
        viewModel.updateTitle("--- Cuentas de $nombre ---")
        viewModel.updateShowBackButton(true)
    }

    Scaffold{
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
