package com.app.pokedexapp.data.repository

import com.app.pokedexapp.data.mapper.toDomain
import com.app.pokedexapp.data.remote.api.PokemonApi
import com.app.pokedexapp.domain.model.Pokemon
import com.app.pokedexapp.domain.repository.PokemonRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton

// Implementamos la interfaz PokemonRepository

// @Singleton indica que solo habrá una instancia
@Singleton
class PokemonRepositoryImpl
    // @Inject constructor para inyección de dependencias
    @Inject
    constructor(
        // Usamos el PokemonApi para hacer las llamadas
        private val api: PokemonApi,
    ) : PokemonRepository {
        override suspend fun getPokemonList(): List<Pokemon> {
            val response = api.getPokemonList()
            return response.results.map { result ->
                // Obtenemos el id de la URL
                val id =
                    result.url
                        .split("/")
                        .dropLast(1)
                        .last()
                // Hacemos la llamada para obtener detalles
                api.getPokemon(id).toDomain()
            }
        }

        override suspend fun getPokemonById(id: String): Pokemon = api.getPokemon(id).toDomain()
    }
