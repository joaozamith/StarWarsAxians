package com.example.starwarsaxians

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.starwarsaxians.ui.screens.characters.details.CharacterDetailsScreen
import com.example.starwarsaxians.ui.screens.characters.list.CharactersListScreen
import com.example.starwarsaxians.ui.screens.dashboard.DashboardScreen
import com.example.starwarsaxians.ui.screens.films.list.FilmsListScreen
import com.example.starwarsaxians.ui.screens.planets.details.PlanetDetailsScreen
import com.example.starwarsaxians.ui.screens.planets.list.PlanetsListScreen
import com.example.starwarsaxians.ui.screens.species.list.SpeciesListScreen
import com.example.starwarsaxians.ui.screens.splash.SplashScreen
import com.example.starwarsaxians.ui.theme.StarWarsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SideEffect { window.statusBarColor = android.graphics.Color.BLACK }

            var showSplash by remember { mutableStateOf(true) }

            if (showSplash) {
                SplashScreen { showSplash = false }
            } else {
                StarWarsTheme {
                    StarWarsAppContent()
                }
            }
        }
    }
}

@Composable
fun StarWarsAppContent() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "dashboard"
    ) {

        composable("dashboard") {
            DashboardScreen(
                onNavigateToCharacters = { navController.navigate("characters_list") },
                onNavigateToFilms = { navController.navigate("films_list") },
                onNavigateToSpecies = { navController.navigate("species_list") },
                onNavigateToPlanets = { navController.navigate("planets_list") },
            )
        }

        composable("characters_list") {
            CharactersListScreen(
                onCharacterClick = { characterId ->
                    navController.navigate("character_details/$characterId")
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            "character_details/{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.StringType })
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId")!!
            CharacterDetailsScreen(
                characterId = characterId,
                onPlanetClick = { planetId ->
                    navController.navigate("planet_details/$planetId")
                }
            )
        }

        composable("films_list") {
            FilmsListScreen(
                onFilmClick = { filmId ->
                    //navController.navigate("film_details/$filmId")
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable("planets_list") {
            PlanetsListScreen(
                onPlanetClick = { planetId ->
                    //navController.navigate("film_details/$filmId")
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable("species_list") {
            SpeciesListScreen(
                onSpeciesClick =  { specieId ->
                    //navController.navigate("film_details/$filmId")
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            "planet_details/{planetId}",
            arguments = listOf(navArgument("planetId") { type = NavType.StringType })
        ) { backStackEntry ->
            val planetId = backStackEntry.arguments?.getString("planetId")!!
            PlanetDetailsScreen(planetId = planetId)
        }
    }
}