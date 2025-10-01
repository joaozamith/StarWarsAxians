package com.example.starwarsaxians

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.starwarsaxians.ui.characters.details.CharacterDetailsScreen
import com.example.starwarsaxians.ui.characters.list.CharactersListScreen
import com.example.starwarsaxians.ui.planets.details.PlanetDetailsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarWarsAppContent()
        }
    }
}

@Composable
fun StarWarsAppContent() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "characters_list"
    ) {
        composable("characters_list") {
            CharactersListScreen(
                onCharacterClick = { characterId ->
                    navController.navigate("character_details/$characterId")
                }
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

        composable(
            "planet_details/{planetId}",
            arguments = listOf(navArgument("planetId") { type = NavType.StringType })
        ) { backStackEntry ->
            val planetId = backStackEntry.arguments?.getString("planetId")!!
            PlanetDetailsScreen(planetId = planetId)
        }
    }
}