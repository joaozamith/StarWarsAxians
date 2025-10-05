package com.example.starwarsaxians.ui.components.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.starwarsaxians.R
import com.example.starwarsaxians.ui.theme.JediYellow
import com.example.starwarsaxians.ui.theme.SithRed
import com.example.starwarsaxians.ui.theme.StarWarsFont
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DashboardDrawer(
    scope: CoroutineScope,
    drawerState: DrawerState,
    isVaderMode: Boolean,
    onFavourites: () -> Unit,
    onPlanetsMap: () -> Unit,
    toggleMode: () -> Unit
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
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NavigationDrawerItem(
                    label = { Text(text = "Favourites", color = MaterialTheme.colorScheme.primary) },
                    selected = false,
                    onClick = { onFavourites() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Map", color = MaterialTheme.colorScheme.primary,) },
                    selected = false,
                    onClick = { onPlanetsMap() }
                )
                Column(
                    modifier = Modifier.padding(top = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Choose your side",
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = StarWarsFont,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.yoda),
                            contentDescription = "Yoda",
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .border(2.dp, if (!isVaderMode) JediYellow else Color.Gray, CircleShape)
                        )

                        Switch(
                            modifier = Modifier
                                .padding(horizontal = 5.dp),
                            checked = isVaderMode,
                            onCheckedChange = { toggleMode() },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = SithRed,
                                checkedTrackColor = SithRed.copy(alpha = 0.6f),
                                uncheckedThumbColor = JediYellow,
                                uncheckedTrackColor = JediYellow.copy(alpha = 0.5f)
                            )
                        )

                        Image(
                            painter = painterResource(id = R.drawable.darthvader),
                            contentDescription = "Darth Vader",
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .border(2.dp, if (isVaderMode) SithRed else Color.Gray, CircleShape)
                        )
                    }
                }
            }
        }
    }
}