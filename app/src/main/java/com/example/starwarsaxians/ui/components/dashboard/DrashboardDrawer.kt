package com.example.starwarsaxians.ui.components.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DashboardDrawer(
     scope: CoroutineScope,
     drawerState: DrawerState
) {
    ModalDrawerSheet(
        modifier = Modifier.fillMaxWidth(0.70f)
    ) {
        IconButton(
            modifier = Modifier.padding(10.dp),
            onClick = { scope.launch { drawerState.close() } }
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close Drawer"
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 62.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NavigationDrawerItem(
                    label = { Text("Favoritos") },
                    selected = false,
                    onClick = { /* TODO */ }
                )
                NavigationDrawerItem(
                    label = { Text("Mapa") },
                    selected = false,
                    onClick = { /* TODO */ }
                )
            }
        }
    }
}