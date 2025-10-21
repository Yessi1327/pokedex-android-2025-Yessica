// Indica que este archivo pertenece al paquete
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

// Sección 1 Define todas las rutas de navegación en un solo lugar

// "sealed class" se usa para definir un conjunto limitado de subclases (en este caso, las pantallas posibles).
// Cada objeto dentro representa una pantalla con su propia ruta.
sealed class Screen(
    // Propiedad que almacena el nombre o patrón de la ruta
    val route: String,
) {
    // Este objeto define una ruta para la pantalla de inicio
    object Home : Screen("home")

    // Est define una ruta para la pantalla de detalles de un Pokémon
    // Remplazando el argumento con {pokemonId} para datos dinamicos
    object Detail : Screen("pokemon/{pokemonId}") {
        // Función auxiliar que crea la ruta completa reemplazando el valor {pokemonId}
        // con el ID real del Pokémon (por ejemplo, "pokemon/3").
        fun createRoute(pokemonId: String) = "pokemon/$pokemonId"
    }
}

// Sección 2: Definición del gráfico de navegación

// Esta anotación desactiva una regla de ktlint que obliga a los nombres de funciones a empezar en minúscula.
// Se permite así que la función se llame "PokemonNavGraph" con mayúscula inicial (estilo Compose).
@Suppress("ktlint:standard:function-naming")
// Definimos la función composable principal del gráfico de navegación.
@Composable
fun PokemonNavGraph(
    // Modifier permite personalizar la apariencia general del contenedor de navegación.
    modifier: Modifier = Modifier,
    // Crea (o recibe) el controlador de navegación. Si no se pasa uno, se crea automáticamente.
    navController: NavHostController = rememberNavController(),
) {
    // NavHost actúa como el contenedor principal de todas las pantallas.
    // Es donde se declara cada "ruta" (pantalla) y su comportamiento.
    NavHost(
        // El controlador que maneja el stack de navegación
        navController = navController,
        // Define qué pantalla se muestra al abrir la app.
        startDestination = Screen.Home.route,
        // Se puede usar para personalizar el aspecto de la pantalla.
        modifier = modifier,
    ) {
        // Primera pantalla: Home

        // "route" es como la dirección de la pantalla
        composable(route = Screen.Home.route) {
            // Llama al composable de la pantalla Home: HomeScreen
            HomeScreen(
                // Parámetro lambda que se ejecuta cuando el usuario toca un Pokémon en la lista.
                onPokemonClick = { pokemonId ->
                    // Navega a la pantalla de detalle con el ID del Pokémon
                    // reemplazando {pokemonId} con el valor real (por ejemplo, "pokemon/1").
                    navController.navigate(Screen.Detail.createRoute(pokemonId))
                },
            )
        }

        // Segunda pantalla: Detalle
        composable(
            // La "ruta" incluye el parámetro pokemonId
            route = Screen.Detail.route,
            // Aquí se define qué tipo de argumento se espera.
            // En este caso, "pokemonId" es un String.
            arguments = listOf(navArgument("pokemonId") { type = NavType.StringType }),
            // backStackEntry contiene los argumentos
        ) { backStackEntry ->

            // Obtiene el pokemonId de los argumentos (usa "1" si no hay ID)
            val pokemonId = backStackEntry.arguments?.getString("pokemonId") ?: "1"

            // Llama al composable de detalle, pasándole el ID y una acción para regresar.
            PokemonDetailScreen(
                // Pasa el ID a la pantalla
                pokemonId = pokemonId,
                // Qué hacer cuando presionan "atrás"
                onBackClick = {
                    // Regresa a la pantalla anterior
                    // popBackStack() quita la pantalla actual del stack y regresa a la anterior.
                    navController.popBackStack()
                },
            )
        }
    }
}
