// Encargada de mostrar la cuadrícula (grid) con todos los Pokémon en la pantalla principal.
package com.app.pokedexapp.presentation.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.pokedexapp.domain.model.Pokemon
import com.app.pokedexapp.presentation.common.components.ErrorView
import com.app.pokedexapp.presentation.common.components.LoadingShimmer

@OptIn(ExperimentalMaterialApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun PokemonListContent(
    // Lista de Pokémon a mostrar.
    pokemonList: List<Pokemon>,
    // Indica si los datos se están cargando.
    isLoading: Boolean,
    // Contiene el mensaje de error si algo salió mal.
    error: String?,
    // Lambda que se ejecuta cuando el usuario hace clic en un Pokémon.
    onPokemonClick: (String) -> Unit,
    onRetry: () -> Unit, // Parámetro agregado
) {
    // rememberPullRefreshState crea el estado para el indicador de carga
    val pullRefreshState =
        rememberPullRefreshState(
            refreshing = isLoading,
            onRefresh = onRetry,
        )
    // Box permite apilar elementos (por ejemplo, centrar textos o el spinner).
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                // Propiedad de pullRefresh
                .pullRefresh(pullRefreshState),
    ) {
        // hacemos uso de los diferentes estados de nuestro resultado y
        // dependiendo los casos, vamos generando diversos resultados
        when {
            isLoading -> {
                // Cargando
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(10) {
                        LoadingShimmer(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .height(160.dp),
                        )
                    }
                }
            }
            error != null && pokemonList.isEmpty() -> {
                ErrorView(
                    message = error,
                    onRetry = onRetry,
                    modifier = Modifier.align(Alignment.Center),
                )
            }
            else -> {
                // LazyVerticalGrid muestra una cuadrícula que se renderiza bajo demanda
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2), // 2 columnas fijas
                    contentPadding = PaddingValues(16.dp), // Margen interno.
                    horizontalArrangement = Arrangement.spacedBy(16.dp), // Espacio entre columnas.
                    verticalArrangement = Arrangement.spacedBy(16.dp), // Espacio entre filas.
                ) {
                    items(
                        items = pokemonList, // Lista de Pokémon.
                        key = { it.id }, // Clave única para cada elemento
                    ) { pokemon ->
                        // Cada Pokémon se muestra en una tarjeta.
                        PokemonCard(
                            pokemon = pokemon,
                            onClick = { onPokemonClick(pokemon.id) },
                        )
                    }
                }
            }
        }

        // Indicador de carga cuando se está cargando.
        PullRefreshIndicator(
            refreshing = isLoading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            scale = true,
        )
    }
}
