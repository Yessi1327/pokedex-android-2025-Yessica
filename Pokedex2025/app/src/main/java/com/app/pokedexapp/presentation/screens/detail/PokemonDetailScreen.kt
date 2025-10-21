package com.app.pokedexapp.presentation.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.app.pokedexapp.domain.model.Pokemon
import com.app.pokedexapp.presentation.screens.detail.components.Chip

// Indica que dentro de esta función se usarán APIs experimentales de Material3 (por ejemplo, TopAppBar).
@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun PokemonDetailScreen(
    // Recibe el ID del Pokémon, pasado desde la pantalla anterior (Home).
    pokemonId: String,
    // Recibe una función que se ejecutará cuando el usuario presione el botón "Atrás".
    onBackClick: () -> Unit,
) {
    val mockPokemon =
        remember {
            Pokemon.getMockData().find { it.id == pokemonId }
        }

    // Scaffold crea la estructura base de esta pantalla: barra superior (topBar) + contenido.
    Scaffold(
        // Sección de la barra superior.
        topBar = {
            // Barra superior de Material Design.
            TopAppBar(
                // Texto que aparece en el centro de la barra superior.
                title = { Text("Detalles del Pokémon") },
                // Ícono de navegación que aparece a la izquierda (el botón de "atrás").
                navigationIcon = {
                    // Botón que envuelve el ícono de flecha.
                    IconButton(onClick = onBackClick) {
                        // Ícono de flecha hacia atrás que, al presionarse
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                },
            )
        },
        // Este lambda recibe el "padding" interno que genera Scaffold.
    ) { padding ->

        // El let es una de las funciones de alcance que Kotlin proporciona
        // para ejecutar un bloque de código dentro del contexto de un objeto.
        mockPokemon?.let { pokemon ->
            // Aquí 'pokemon' es una versión no-nullable de mockPokemon
            // El código aquí solo se ejecuta si mockPokemon no es null
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AsyncImage(
                    model = pokemon.imageUrl,
                    contentDescription = pokemon.name,
                    modifier = Modifier.size(200.dp),
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = pokemon.name,
                    style = MaterialTheme.typography.headlineMedium,
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Basic info
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Height")
                        Text("${pokemon.height / 10.0}m")
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Weight")
                        Text("${pokemon.weight / 10.0}kg")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Types
                Text("Types", style = MaterialTheme.typography.titleMedium)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    pokemon.types.forEach { type ->
                        Chip(type = type)
                    }
                }
            }
        }
    }
}
