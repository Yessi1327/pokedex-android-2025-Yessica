package com.app.pokedexapp.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.app.pokedexapp.domain.model.Pokemon
import com.app.pokedexapp.presentation.screens.home.components.PokemonListTab
import com.app.pokedexapp.presentation.screens.home.components.SearchTab

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
// Composable principal de la pantalla Home.
// Muestra la barra superior, las pestañas y el contenido de cada una.
@Composable
fun HomeScreen(onPokemonClick: (String) -> Unit) {
    // Variable que guarda el índice de la pestaña actualmente seleccionada (0 o 1).
    // "remember" hace que el valor se conserve mientras la composición esté activa.
    // "mutableStateOf" crea un estado observable que redibuja la UI cuando cambia.
    var selectedTabIndex by remember { mutableStateOf(0) }

    // Lista de nombres de las pestañas. Se usan para generar dinámicamente los botones de Tab.
    val tabs = listOf("Pokémon List", "Search")
    val mockPokemonList = remember { Pokemon.getMockData() }

    // Scaffold crea una estructura base con una barra superior y un área de contenido.
    Scaffold(
        // Barra superior centrada, usando el componente de Material3.
        topBar = {
            CenterAlignedTopAppBar(
                // Título centrado en la barra superior.
                title = { Text("Pokédex") },
            )
        },
        // Este lambda recibe el padding automático del Scaffold.
    ) { padding ->

        // Organiza los elementos verticalmente (barra de pestañas + contenido).
        Column(
            modifier =
                Modifier
                    // Ocupa todo el tamaño disponible de la pantalla.
                    .fillMaxSize()
                    // Aplica el padding interno que genera Scaffold
                    .padding(padding),
        ) {
            // TabRow dibuja las pestañas horizontales (como una barra superior con dos opciones).
            TabRow(selectedTabIndex = selectedTabIndex) {
                // Genera dinámicamente las pestañas a partir de la lista "tabs".
                tabs.forEachIndexed { index, title ->

                    // Cada pestaña muestra su título y cambia de color cuando está seleccionada.
                    Tab(
                        text = { Text(title) },
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                    )
                }
            }

            // Dependiendo de la pestaña seleccionada, muestra una u otra pantalla.
            when (selectedTabIndex) {
                0 ->
                    PokemonListTab(
                        pokemonList = mockPokemonList,
                        onPokemonClick = onPokemonClick,
                    )
                1 -> SearchTab(onPokemonClick = onPokemonClick)
            }
        }
    }
}
