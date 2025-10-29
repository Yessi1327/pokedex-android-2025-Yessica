// El estado completo de la pantalla principal (HomeScreen)
package com.app.pokedexapp.presentation.screens.home

import com.app.pokedexapp.domain.model.Pokemon

// Definición de la clase de datos (data class).
data class HomeUiState(
    // Lista de Pokémon que se mostrará en la pantalla principal.
    val pokemonList: List<Pokemon> = emptyList(),
    // Se usa para mostrar el CircularProgressIndicator.
    val isLoading: Boolean = false,
    // Mensaje de error si ocurre un fallo al obtener los Pokémon.
    val error: String? = null,
)
