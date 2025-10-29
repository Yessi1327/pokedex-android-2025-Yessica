package com.app.pokedexapp.domain.repository

import com.app.pokedexapp.domain.model.Pokemon

// Domain no necesita saber de dónde vienen los datos
// Podrían venir de API, base de datos, cache, etc.
interface PokemonRepository {
    suspend fun getPokemonList(): List<Pokemon>

    suspend fun getPokemonById(id: String): Pokemon
}
