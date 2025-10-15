package com.app.pokedexapp.presentation.screens.home

import androidx.compose.runtime.Composable

@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreen(onPokemonClick: (String) -> Unit) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Pokémon List", "Search")

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Pokédex") },
            )
        },
    ) { padding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(padding),
        ) {
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                    )
                }
            }

            when (selectedTabIndex) {
                0 -> PokemonListTab(onPokemonClick = onPokemonClick)
                1 -> SearchTab(onPokemonClick = onPokemonClick)
            }
        }
    }
}
