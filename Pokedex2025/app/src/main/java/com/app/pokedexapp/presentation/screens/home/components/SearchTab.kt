package com.app.pokedexapp.presentation.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

// Crea una columna vertical para organizar los elementos (título y mensaje)
@Suppress("ktlint:standard:function-naming")
// Define el composable que representa la pestaña de "Búsqueda" de la app.
@Composable
fun SearchTab(onPokemonClick: (String) -> Unit) {
    // Crea una columna vertical para organizar los elementos (título y mensaje).
    Column(
        // Define las características visuales de la columna.
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        // Centra horizontalmente todo el contenido dentro de la columna.
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Muestra el título de la pestaña.
        Text(
            text = "Búsqueda de Pokémon", // Texto que se muestra en la parte superior.
            style = MaterialTheme.typography.headlineMedium, // Usa estilo de título grande del tema.
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Texto secundario que indica que esta función aún no está implementada.
        Text(
            text = "Funcionalidad disponible próximamente",
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}
