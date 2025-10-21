package com.app.pokedexapp.presentation.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

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

        // Usa un Column para colocar los elementos uno debajo del otro.
        Column(
            // Modifier define las características visuales del Column.
            modifier =
                Modifier
                    // Ocupa todo el alto y ancho disponible.
                    .fillMaxSize()
                    // Aplica el padding automático del Scaffold (para no quedar bajo la barra superior).
                    .padding(padding)
                    // Agrega un padding extra de 16 dp en todos los lados.
                    .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Muestra una imagen desde Internet usando Coil.
            // Mock data para el Lab 3
            // En este caso, la URL se genera dinámicamente con el ID del Pokémon.
            AsyncImage(
                // URL de la imagen del Pokémon, tomada del repositorio de sprites de la PokeAPI.
                model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonId.png",
                contentDescription = "Pokemon $pokemonId",
                // Tamaño de la imagen.
                modifier = Modifier.size(200.dp),
            )

            // Inserta un espacio vacío de 16 dp entre la imagen y el texto.
            Spacer(modifier = Modifier.height(16.dp))

            // Muestra el texto con el número del Pokémon actual.
            Text(
                text = "Pokemon #$pokemonId",
                // Define el estilo tipográfico del texto
                style = MaterialTheme.typography.headlineMedium,
            )
        }
    }
}
