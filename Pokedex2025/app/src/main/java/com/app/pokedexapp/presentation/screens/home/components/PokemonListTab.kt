package com.app.pokedexapp.presentation.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Suppress("ktlint:standard:function-naming")
// Define el composable que representa la pestaña de lista de Pokémon (una cuadrícula de tarjetas).
@Composable
fun PokemonListTab(onPokemonClick: (String) -> Unit) {
    // Crea una cuadrícula vertical
    LazyVerticalGrid(
        // Define que la cuadrícula tendrá siempre 2 columnas fijas.
        columns = GridCells.Fixed(2),
        // Define el espacio interno alrededor de todo el contenido del grid
        contentPadding = PaddingValues(16.dp),
        // Define el espaciado horizontal entre columnas.
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        // Define el espaciado vertical entre filas.
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // "items(5)" genera 5 elementos de prueba (mock data) para el laboratorio.
        // En una app real, aquí irían los datos del backend o de una base local.
        // Mock data para el Lab 3
        items(5) { index ->

            // Cada elemento de la lista crea una tarjeta de Pokémon reutilizando el composable PokemonCard.
            PokemonCard(
                // Nombre que aparecerá debajo de la imagen.
                name = "Pokemon ${index + 1}",
                // URL de la imagen (sprite) tomada de la API
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${index + 1}.png",
                // Se pasa el "index" convertido a String para navegar al detalle del Pokémon.
                onClick = { onPokemonClick(index.toString()) },
            )
        }
    }
}
