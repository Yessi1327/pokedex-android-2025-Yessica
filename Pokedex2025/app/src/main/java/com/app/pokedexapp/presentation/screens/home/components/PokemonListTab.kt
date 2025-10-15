package com.app.pokedexapp.presentation.screens.home.components

@Composable
fun PokemonListTab(onPokemonClick: (String) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // Mock data para el Lab 3
        items(5) { index ->
            PokemonCard(
                name = "Pokemon ${index + 1}",
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${index + 1}.png",
                onClick = { onPokemonClick(index.toString()) },
            )
        }
    }
}
