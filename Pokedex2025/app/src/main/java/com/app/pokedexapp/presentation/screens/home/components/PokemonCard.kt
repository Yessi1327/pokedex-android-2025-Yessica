package com.app.pokedexapp.presentation.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.app.pokedexapp.domain.model.Pokemon

@Suppress("ktlint:standard:function-naming")
// Función composable que define cómo se ve una tarjeta de Pokémon en la lista.
@Composable
fun PokemonCard(
    // data desde el data class
    pokemon: Pokemon,
    onClick: () -> Unit,
) {
    // Crea una tarjeta (Card) de Material3.
    Card(
        // Define el comportamiento y aspecto del Card mediante Modifier.
        modifier =
            Modifier
                .fillMaxWidth()
                // Permite que sea clickeable y ejecute la función onClick al presionarla.
                .clickable(onClick = onClick),
        // Controla la sombra o profundidad visual
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        // Dentro de la tarjeta, organizamos los elementos en columna:
        // primero la imagen, luego el texto.
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Carga y muestra la imagen del Pokémon desde Internet.
            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = pokemon.name,
                modifier =
                    Modifier
                        .size(120.dp)
                        .padding(8.dp),
            )

            Text(
                text = pokemon.name,
                style = MaterialTheme.typography.titleMedium,
                // Centra el texto horizontalmente.
                textAlign = TextAlign.Center,
            )
        }
    }
}
