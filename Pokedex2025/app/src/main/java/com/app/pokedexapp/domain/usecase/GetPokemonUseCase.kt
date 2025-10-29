package com.app.pokedexapp.domain.usecase

// Antes tenia import jakarta.inject.Inject y no esta bien
// Tuve que cambiar todos las Jakarta tanto de inject como de Singleton
import com.app.pokedexapp.domain.common.Result
import com.app.pokedexapp.domain.model.Pokemon
import com.app.pokedexapp.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

// Esta a diferencia de GetPokemonList es que esta recupera un solo pokemon
// y no una lista de pokemons
class GetPokemonUseCase
    @Inject
    constructor(
        private val repository: PokemonRepository,
    ) {
        operator fun invoke(id: String): Flow<Result<Pokemon>> =
            flow {
                try {
                    emit(Result.Loading)
                    val pokemon = repository.getPokemonById(id)
                    emit(Result.Success(pokemon))
                } catch (e: Exception) {
                    emit(Result.Error(e))
                }
            }
    }
