// Define la pantalla completa de detalle de un Pokémon individual.
package com.app.pokedexapp.presentation.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.app.pokedexapp.domain.model.Pokemon
import com.app.pokedexapp.presentation.common.components.ErrorView
import com.app.pokedexapp.presentation.common.components.LoadingShimmer
import com.app.pokedexapp.presentation.screens.detail.components.Chip
import com.app.pokedexapp.presentation.screens.detail.components.PokemonDetailContent

// Indica que dentro de esta función se usarán APIs experimentales de Material3 (por ejemplo, TopAppBar).
@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun PokemonDetailScreen(
    // Recibe el ID del Pokémon, pasado desde la pantalla anterior (Home).
    pokemonId: String,
    // Recibe una función que se ejecutará cuando el usuario presione el botón "Atrás".
    onBackClick: () -> Unit,
    // Inyecta el ViewModel con Hilt (sin tener que crearlo manualmente).
    viewModel: PokemonDetailViewModel = hiltViewModel(),
) {
    // Recolecta el estado del ViewModel
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Manejar efectos secundarios de manera segura y
    // eficiente dentro del ciclo de vida de los composables.
    // // LaunchedEffect se ejecutará cada vez que cambie pokemonId
    LaunchedEffect(pokemonId) {
        // // Inicia la carga del Pokémon cuando el ID cambia
        viewModel.getPokemon(pokemonId)
    }

    // Scaffold crea la estructura base de esta pantalla: barra superior (topBar) + contenido.
    Scaffold(
        // Sección de la barra superior.
        topBar = {
            // Barra superior de Material Design.
            TopAppBar(
                // Texto que aparece en el centro de la barra superior.
                title = { Text("Detalles del Pokémon") },
                // Ícono de navegación que aparece a la izquierda (el botón de "atrás").
                navigationIcon = {
                    // Botón que envuelve el ícono de flecha.
                    IconButton(onClick = onBackClick) {
                        // Ícono de flecha hacia atrás que, al presionarse
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                },
            )
        },
        // Este lambda recibe el "padding" interno que genera Scaffold.
    ) { padding ->

        // Box permite superponer elementos (por ejemplo, centrar un indicador de carga).
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(padding),
        ) {
            // // Se evalúan los posibles estados del ViewModel.
            when {
                // Si está cargando, muestra el indicador circular en el centro.
                uiState.isLoading -> {
                    LoadingShimmer(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                    )
                }
                // Si hubo un error, muestra el mensaje en rojo.
                uiState.error != null -> {
                    // Usar Componente de Error
                    ErrorView(
                        message = uiState.error ?: "Unknown error",
                        onRetry = { viewModel.getPokemon(pokemonId) },
                        modifier = Modifier.align(Alignment.Center),
                    )
                }
                // Si ya se obtuvo el Pokémon, muestra el contenido detallado.
                uiState.pokemon != null -> {
                    PokemonDetailContent(
                        // Manda el objeto al composable visual.
                        pokemon = uiState.pokemon!!,
                    )
                }
            }
        }
    }
}
