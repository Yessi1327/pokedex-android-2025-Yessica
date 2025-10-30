package com.app.pokedexapp.presentation.screens.home.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
    // variable para detectar cuando hagamos click en la Card
    var isPressed by remember { mutableStateOf(false) }

    // Es un valor float que se utilizará para escalar un componente
    // Es una función de Compose que crea una animación suave entre dos
    val scale by animateFloatAsState(
        // el valor objetivo es 0.95f (95% del tamaño original)
        targetValue = if (isPressed) 0.95f else 1f,
        label = "",
    )
    // Crea una tarjeta (Card) de Material3.
    Card(
        // Define el comportamiento y aspecto del Card mediante Modifier.
        modifier =
            Modifier
                .fillMaxWidth()
                // Permite que sea clickeable y ejecute la función onClick al presionarla.
                .clickable(onClick = onClick)
                .scale(scale)
                // permite manejar eventos de entrada (toques, gestos, etc.)
                .pointerInput(Unit) {
                    // es una función que detecta diferentes tipos de gestos táctiles
                    detectTapGestures(
                        // es uno de los callbacks disponibles
                        onPress = {
                            isPressed = true // Marca el componente como presionado
                            tryAwaitRelease() // Espera de forma suspendida a que el usuario suelte
                            isPressed = false // Cuando se suelta, marca como no presionado
                            onClick() // Ejecuta la acción definida
                        },
                    )
                },
        // Controla la sombra o profundidad visual
        // defaultElevation sombra al Card al momento de hacerle presión encima.
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 4.dp,
                pressedElevation = 8.dp,
            ),
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
                contentScale = ContentScale.Fit,
            )

            Text(
                text = pokemon.name,
                style = MaterialTheme.typography.titleMedium,
                // Centra el texto horizontalmente.
                textAlign = TextAlign.Center,
                // maxLineslimita la cantidad de líneas que puede desplegar el Text
                maxLines = 1,
                // nos permite añadir un comportamiento cuando un texto es más grande
                // los modificadores de texto definidos de android, en forma más simple
                // si un texto se sobrepasa de la línea añade ...
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
