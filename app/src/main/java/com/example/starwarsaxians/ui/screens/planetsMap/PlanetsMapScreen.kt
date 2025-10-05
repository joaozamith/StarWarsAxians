package com.example.starwarsaxians.ui.screens.planetsMap

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.starwarsaxians.ui.theme.StarWarsFont
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.BoundingBox
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetsMapScreen(
    viewModel: PlanetsMapViewModel = hiltViewModel()
) {
    val planets by viewModel.planets.collectAsState()
    val selectedPlanet by viewModel.selectedPlanet.collectAsState()
    val planetCharacters by viewModel.planetCharacters.collectAsState()
    val isLoadingCharacters by viewModel.isLoadingCharacters.collectAsState()

    val context = LocalContext.current
    val mapView = remember {
        MapView(context).apply {
            setTileSource(TileSourceFactory.MAPNIK)
            controller.setZoom(2.0)
            controller.setCenter(GeoPoint(10.0, 20.0))
            setMultiTouchControls(true)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(factory = { mapView }, modifier = Modifier.fillMaxSize()) { map ->
            map.overlays.clear()
            map.isHorizontalMapRepetitionEnabled = false
            map.isVerticalMapRepetitionEnabled = false

            val north = 85.0
            val south = -85.0
            val west = -180.0
            val east = 180.0
            map.setScrollableAreaLimitDouble(BoundingBox(north, east, south, west))

            map.minZoomLevel = 1.5
            map.maxZoomLevel = 5.5

            map.setMultiTouchControls(true)
            map.controller.setZoom(2.5)
            map.controller.setCenter(GeoPoint(10.0, 20.0))

            planets.forEachIndexed { index, planet ->
                val lat = (index * 15 % 90) - 45.0
                val lon = (index * 40 % 180) - 90.0

                val marker = Marker(map).apply {
                    position = GeoPoint(lat, lon)
                    title = planet.name
                    setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                    setOnMarkerClickListener { _, _ ->
                        viewModel.selectPlanet(planet)
                        true
                    }
                }
                map.overlays.add(marker)
            }

            map.invalidate()
        }

        if (selectedPlanet != null) {
            AlertDialog(
                onDismissRequest = { viewModel.clearSelection() },
                confirmButton = {
                    TextButton(onClick = { viewModel.clearSelection() }) {
                        Text("Close", color = MaterialTheme.colorScheme.primary)
                    }
                },
                title = {
                    Text(
                        text = selectedPlanet?.name ?: "",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontFamily = StarWarsFont,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                },
                text = {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Climate: ${selectedPlanet?.climate ?: "-"}",
                            color = Color.White
                        )
                        Text(
                            text = "Terrain: ${selectedPlanet?.terrain ?: "-"}",
                            color = Color.White
                        )

                        Divider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                        )

                        Text(
                            text = "Characters from this planet:",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontFamily = StarWarsFont
                            )
                        )

                        when {
                            isLoadingCharacters -> Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                            }

                            planetCharacters.isEmpty() -> Text(
                                text = "No known residents.",
                                color = Color.LightGray,
                                style = MaterialTheme.typography.bodySmall
                            )

                            else -> planetCharacters.forEach {
                                Text(
                                    text = "• ${it.name}",
                                    color = Color.White,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                },
                containerColor = Color(0xFF101010),
                tonalElevation = 8.dp,
                shape = RoundedCornerShape(12.dp)
            )
        }
    }
}
