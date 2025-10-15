package com.app.pokedexapp.presentation.screens.home.components

@Suppress("ktlint:standard:function-naming")
@Composable
fun SearchTab(onPokemonClick: (String) -> Unit) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Búsqueda de Pokémon",
            style = MaterialTheme.typography.headlineMedium,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Funcionalidad disponible próximamente",
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}
