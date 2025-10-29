// Encargada de mostrar la cuadrícula (grid) con todos los Pokémon en la pantalla principal.
package com.app.pokedexapp.presentation.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.pokedexapp.domain.model.Pokemon

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
) {
    // Box permite apilar elementos (por ejemplo, centrar textos o el spinner).
    Box(modifier = Modifier.fillMaxSize()) {
        // hacemos uso de los diferentes estados de nuestro resultado y
        // dependiendo los casos, vamos generando diversos resultados
        when {
            isLoading -> {
                // Cargando
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                )
            }
            error != null -> {
                // Error
                Text(
                    text = error,
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.error,
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
    }
}
