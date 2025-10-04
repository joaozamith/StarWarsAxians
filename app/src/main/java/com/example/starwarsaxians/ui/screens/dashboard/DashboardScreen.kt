package com.example.starwarsaxians.ui.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.starwarsaxians.R
import com.example.starwarsaxians.ui.components.dashboard.DashboardDrawer
import com.example.starwarsaxians.ui.components.StarWarsBackground
import com.example.starwarsaxians.ui.components.dashboard.DashboardCard
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onNavigateToCharacters: () -> Unit,
    onNavigateToFilms: () -> Unit,
    onNavigateToSpecies: () -> Unit,
    onNavigateToPlanets: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    StarWarsBackground {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = { DashboardDrawer(scope, drawerState) }
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
                                Icon(Icons.Default.Menu, contentDescription = "Menu")
                            }
                        },
                    )
                },
                containerColor = Color.Transparent
            ) { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
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