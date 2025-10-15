package com.app.pokedexapp.presentation.screens.home.components

@Composable
fun PokemonCard(
    name: String,
    imageUrl: String,
    onClick: () -> Unit,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = name,
                modifier =
                    Modifier
                        .size(120.dp)
                        .padding(8.dp),
            )

            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
            )
        }
    }
}
