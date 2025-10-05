package com.example.starwarsaxians

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.starwarsaxians.ui.screens.characters.details.CharacterDetailsScreen
import com.example.starwarsaxians.ui.screens.characters.list.CharactersListScreen
import com.example.starwarsaxians.ui.screens.dashboard.DashboardScreen
import com.example.starwarsaxians.ui.screens.favourites.FavoritesScreen
import com.example.starwarsaxians.ui.screens.films.list.FilmsListScreen
import com.example.starwarsaxians.ui.screens.planets.details.PlanetDetailsScreen
import com.example.starwarsaxians.ui.screens.planets.list.PlanetsListScreen
import com.example.starwarsaxians.ui.screens.planetsMap.PlanetsMapScreen
import com.example.starwarsaxians.ui.screens.species.list.SpeciesListScreen
import com.example.starwarsaxians.ui.screens.splash.SplashScreen
import com.example.starwarsaxians.ui.theme.AppThemeViewModel
import com.example.starwarsaxians.ui.theme.StarWarsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        org.osmdroid.config.Configuration.getInstance().userAgentValue = packageName
        setContent {
            SideEffect { window.statusBarColor = android.graphics.Color.BLACK }

            val appThemeViewModel: AppThemeViewModel = hiltViewModel()
            val isDarthVaderMode by appThemeViewModel.isDarthVaderMode.collectAsState()
            var showSplash by remember { mutableStateOf(true) }

            if (showSplash) {
                SplashScreen { showSplash = false }
            } else {
                StarWarsTheme(isDarthVaderMode = isDarthVaderMode) {
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
                onPlanetsMap = { navController.navigate("planets_map") },
                onFavourites = { navController.navigate("favourites_screen") }
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
                onBack = { navController.popBackStack() }
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

        composable("favourites_screen") {
            FavoritesScreen(
                onCharacterClick =  { characterId ->
                    navController.navigate("character_details/$characterId")
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

        composable(
            "planets_map",
        ) {
            PlanetsMapScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}