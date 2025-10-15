package com.app.pokedexapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.pokedexapp.presentation.screens.detail.PokemonDetailScreen
import com.app.pokedexapp.presentation.screens.home.HomeScreen

// Centraliza todas las rutas de navegación en un solo lugar
sealed class Screen(
    val route: String,
) {
    // Esto define una ruta para la pantalla de inicio
    object Home : Screen("home")

    // Est define una ruta para la pantalla de detalles de un Pokémon remplazando el argumento con {pokemonId}
    object Detail : Screen("pokemon/{pokemonId}") {
        fun createRoute(pokemonId: String) = "pokemon/$pokemonId"
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun PokemonNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        // NavHost es como un "contenedor" que maneja todas nuestras pantallas
        navController = navController, // El controlador que maneja la navegación
        startDestination = Screen.Home.route, // Indica qué pantalla se muestra primero
        modifier = modifier, // Para personalizar el aspecto si es necesario
    ) {
        // "route" es como la dirección de la pantalla
        composable(route = Screen.Home.route) {
            HomeScreen(
                // Qué hacer cuando clickean un Pokémon
                onPokemonClick = { pokemonId ->
                    // Navega a la pantalla de detalle con el ID del Pokémon
                    navController.navigate(Screen.Detail.createRoute(pokemonId))
                },
            )
        }

        // Segunda pantalla: Detalle
        composable(
            // La "ruta" incluye el parámetro pokemonId
            route = Screen.Detail.route,
            // Define qué tipo de dato es pokemonId
            arguments = listOf(navArgument("pokemonId") { type = NavType.StringType }),
            // backStackEntry contiene los argumentos
        ) { backStackEntry ->
            // Obtiene el pokemonId de los argumentos (usa "1" si no hay ID)
            val pokemonId = backStackEntry.arguments?.getString("pokemonId") ?: "1"

            PokemonDetailScreen(
                // Pasa el ID a la pantalla
                pokemonId = pokemonId,
                // Qué hacer cuando presionan "atrás"
                onBackClick = {
                    // Regresa a la pantalla anterior
                    navController.popBackStack()
                },
            )
        }
    }
}
