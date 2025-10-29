// PokemonDetailViewModel se encarga de controlar la pantalla de detalle del Pokémon
package com.app.pokedexapp.presentation.screens.detail

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pokedexapp.domain.common.Result
import com.app.pokedexapp.domain.usecase.GetPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// Indica a Hilt que este ViewModel puede inyectar dependencias
@HiltViewModel
class PokemonDetailViewModel
    @Inject
    constructor(
        private val getPokemonUseCase: GetPokemonUseCase,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(PokemonDetailUiState())

        // Se usa .asStateFlow() para no permitir que otras clases modifiquen el estado.
        val uiState: StateFlow<PokemonDetailUiState> = _uiState.asStateFlow()

        // Ejecuta el caso de uso (que devuelve un Flow<Result<Pokemon>>)
        fun getPokemon(id: String) {
            viewModelScope.launch {
                getPokemonUseCase(id).collect { result ->
                    // Actualiza el flujo de estado de forma segura
                    _uiState.update { state ->
                        // Dependiendo del tipo de resultado, se actualiza el estado.
                        when (result) {
                            is Result.Loading ->
                                // Copia el estado actual, pero establece isLoading = true.
                                state.copy(
                                    isLoading = true,
                                )
                            is Result.Success ->
                                // Copia el estado, actualizando el Pokémon y apagando el loading.
                                state.copy(
                                    pokemon = result.data,
                                    isLoading = false,
                                    error = null,
                                )
                            is Result.Error ->
                                // Copia el estado, guardando el mensaje de error y deteniendo el loading.
                                state.copy(
                                    error = result.exception.message,
                                    isLoading = false,
                                )
                        }
                    }
                }
            }
        }
    }
