// Define la pantalla completa de detalle de un Pokémon individual.
// Es lo que tu PokemonDetailViewModel expone para que la
// interfaz (PokemonDetailScreen) sepa qué dibujar en cada momento.
package com.app.pokedexapp.presentation.screens.detail

import com.app.pokedexapp.domain.model.Pokemon

// Define una clase de datos (data class) que modela el
// estado visual de la pantalla de detalle.
data class PokemonDetailUiState(
    // Pokémon actualmente mostrado en pantalla.
    val pokemon: Pokemon? = null,
    // Indica si la pantalla está en proceso de carga.
    val isLoading: Boolean = false,
    // Contiene el mensaje de error si algo falla
    val error: String? = null,
)
