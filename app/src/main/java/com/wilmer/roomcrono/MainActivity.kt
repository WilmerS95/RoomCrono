package com.wilmer.roomcrono

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.wilmer.roomcrono.components.crono.AppToolbar
import com.wilmer.roomcrono.navigation.NavManager
import com.wilmer.roomcrono.ui.theme.RoomCronoTheme
import com.wilmer.roomcrono.view_models.accounts.AccountsViewModel
import com.wilmer.roomcrono.view_models.crono.CronometroViewModel
import com.wilmer.roomcrono.view_models.crono.CronosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cronometroViewModel : CronometroViewModel by viewModels()
        val cronosViewModel : CronosViewModel by viewModels()
        val accountsViewModel : AccountsViewModel by viewModels()
        setContent {
            RoomCronoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        AppToolbar(title = "--- Crono App ---"){}
                        NavManager(cronometroViewModel, cronosViewModel, accountsViewModel)
                    }
                }
            }
        }
    }
}