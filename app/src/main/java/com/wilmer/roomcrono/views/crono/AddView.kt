package com.wilmer.roomcrono.views.crono

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.wilmer.roomcrono.R
import com.wilmer.roomcrono.components.crono.CircleButton
import com.wilmer.roomcrono.components.crono.MainButton
import com.wilmer.roomcrono.components.crono.MainTextField
import com.wilmer.roomcrono.components.crono.MainTitle
import com.wilmer.roomcrono.components.crono.formatoTiempo
import com.wilmer.roomcrono.model.cronos.CronoModel
import com.wilmer.roomcrono.view_models.crono.CronometroViewModel
import com.wilmer.roomcrono.view_models.crono.CronosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddView(navController: NavController, cronometroViewModel: CronometroViewModel, cronosViewModel: CronosViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    MainTitle(title = "--- Agregar Crono ---")
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    MainButton(icon = Icons.Default.ArrowBack) {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) {
        ContentAddView(it, navController, cronometroViewModel, cronosViewModel)
    }
}

@Composable
fun ContentAddView(
    it: PaddingValues,
    navController: NavController,
    cronometroViewModel: CronometroViewModel,
    cronosViewModel: CronosViewModel
) {
    val state = cronometroViewModel.state

    LaunchedEffect(state.cronometroActivo) {
        cronometroViewModel.cronos()
    }

    Column(
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatoTiempo(cronometroViewModel.tiempo),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(vertical = 16.dp)
        ) {
            //Iniciar
            CircleButton(
                icon = painterResource(R.drawable.play),
                enabled = !state.cronometroActivo
            ) {
                cronometroViewModel.iniciar()
            }
            //Pausar
            CircleButton(
                icon = painterResource(R.drawable.pause),
                enabled = state.cronometroActivo
            ) {
                cronometroViewModel.pausar()
            }
            //Detener
            CircleButton(
                icon = painterResource(R.drawable.stop),
                enabled = state.cronometroActivo,
            ) {
                cronometroViewModel.detener()
            }
            //Mostrar Guardar
            CircleButton(
                icon = painterResource(R.drawable.save),
                enabled = state.showSaveButton
            ) {
                cronometroViewModel.showTextField()
            }
        }
        if (state.showTextField) {
            MainTextField(
                value = state.title,
                onValueChange = { cronometroViewModel.onValue(it) },
                label = "Title"
            )
            Button(onClick = {
                cronosViewModel.addCrono(
                    CronoModel(
                        title = state.title,
                        crono = cronometroViewModel.tiempo
                    )
                )
                cronometroViewModel.detener()
                navController.popBackStack()
            }){
                Text(text = "Guardar")
            }
            /*DisposableEffect(Unit){
                onDispose {
                    cronometroViewModel.detener()
                }
            }*/
        }
    }
}