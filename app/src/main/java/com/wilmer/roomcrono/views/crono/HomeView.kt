package com.wilmer.roomcrono.views.crono

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wilmer.roomcrono.components.crono.CronoCard
import com.wilmer.roomcrono.components.crono.FloatButton
import com.wilmer.roomcrono.components.crono.MainTitle
import com.wilmer.roomcrono.components.crono.formatoTiempo
import com.wilmer.roomcrono.k_form.activities.FormExampleActivity
import com.wilmer.roomcrono.k_form.fragments.FormExampleFragment
import com.wilmer.roomcrono.view_models.accounts.AccountsViewModel
import com.wilmer.roomcrono.view_models.crono.CronosViewModel
import com.wilmer.roomcrono.views.accounts.AccountsView
import kotlinx.coroutines.launch
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeView(navController: NavController, cronosViewModel: CronosViewModel, accountsViewModel: AccountsViewModel) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val scope = rememberCoroutineScope()
    val tabTitles = listOf("Cronómetro", "Cuentas", "Formularios Dinámicos")

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    MainTitle(title = "--- Crono App ---")
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            if (pagerState.currentPage == 0) {
                FloatButton {
                    navController.navigate("AddView")
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            TabRow(selectedTabIndex = pagerState.currentPage) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                        text = { Text(title) }
                    )
                }
            }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                when (page) {
                    0 -> ContentHomeView(navController, cronosViewModel)
                    1 -> AccountsView(viewModel = accountsViewModel, navController = navController)
                    2 -> FormExampleActivity()
                }
            }
        }
    }
}

@Composable
fun ContentHomeView(
    navController: NavController,
    cronosViewModel: CronosViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val cronosList by cronosViewModel.cronosList.collectAsState()
        LazyColumn {
            items(cronosList) { item ->
                val delete = SwipeAction(
                    icon = rememberVectorPainter(Icons.Default.Delete),
                    background = Color.Red,
                    onSwipe = {
                        cronosViewModel.deleteCrono(item)
                    }
                )
                SwipeableActionsBox(
                    startActions = listOf(delete),
                    endActions = listOf(delete),
                    swipeThreshold = 200.dp
                ) {
                    CronoCard(item.title, formatoTiempo(item.crono)) {
                        navController.navigate("EditView/${item.id}")
                    }
                }
            }
        }
    }
}