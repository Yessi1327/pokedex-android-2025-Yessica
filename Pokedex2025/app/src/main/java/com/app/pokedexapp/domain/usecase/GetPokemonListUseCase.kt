package com.app.pokedexapp.domain.usecase

import com.app.pokedexapp.domain.common.Result
import com.app.pokedexapp.domain.model.Pokemon
import com.app.pokedexapp.domain.repository.PokemonRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// Inyección de Dependencias:
// El UseCase no crea el repository, lo recibe ya creado
class GetPokemonListUseCase
    // @Inject constructor le dice a Hilt: "Inyecta las dependencias en este constructor"
    @Inject
    constructor(
        private val repository: PokemonRepository,
    ) {
        // operator fun invoke() permite llamar la clase como si fuera una función
        // En vez de useCase.execute(), podemos usar useCase()
        operator fun invoke(): Flow<Result<List<Pokemon>>> =
            // Flow es como un río de datos que puede emitir múltiples valores a lo largo del tiempo
            // A diferencia de una función normal que retorna un solo valor
            // Estados de carga (Loading → Success/Error)
            flow {
                try {
                    // Primer valor: Loading
                    emit(Result.Loading)

                    // Obtiene datos
                    val pokemonList = repository.getPokemonList()
                    // Segundo valor: Success con datos
                    emit(Result.Success(pokemonList))
                } catch (e: Exception) {
                    // O Error si algo falla
                    emit(Result.Error(e))
                }
            }
    }
