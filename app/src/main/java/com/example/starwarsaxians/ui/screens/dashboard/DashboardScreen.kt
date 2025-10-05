package com.example.starwarsaxians.ui.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.starwarsaxians.R
import com.example.starwarsaxians.ui.components.StarWarsBackground
import com.example.starwarsaxians.ui.components.dashboard.DashboardCard
import com.example.starwarsaxians.ui.components.dashboard.DashboardDrawer
import com.example.starwarsaxians.ui.theme.AppThemeViewModel
import com.example.starwarsaxians.ui.theme.StarWarsTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onNavigateToCharacters: () -> Unit,
    onNavigateToFilms: () -> Unit,
    onNavigateToSpecies: () -> Unit,
    onNavigateToPlanets: () -> Unit,
    onFavourites: () -> Unit,
    viewModel: AppThemeViewModel = hiltViewModel()
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val isVaderMode by viewModel.isDarthVaderMode.collectAsState()

    StarWarsTheme(isDarthVaderMode = isVaderMode) {
        StarWarsBackground {
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    DashboardDrawer(
                        scope = scope,
                        drawerState = drawerState,
                        isVaderMode = isVaderMode,
                        onFavourites = { onFavourites() },
                        toggleMode = { viewModel.toggleMode() }
                    )
                }
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(end = 50.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.starwars_logo),
                                        contentDescription = "Star Wars Logo",
                                        modifier = Modifier.size(100.dp)
                                    )
                                }
                            },
                            navigationIcon = {
                                IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = "Menu"
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
                        )
                    },
                    containerColor = Color.Transparent
                ) { padding ->
                    Column(
                        modifier = Modifier
                            .padding(padding)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        DashboardCard("Characters", onNavigateToCharacters)
                        DashboardCard("Films", onNavigateToFilms)
                        DashboardCard("Species", onNavigateToSpecies)
                        DashboardCard("Planets", onNavigateToPlanets)
                    }
                }
            }
        }
    }
}