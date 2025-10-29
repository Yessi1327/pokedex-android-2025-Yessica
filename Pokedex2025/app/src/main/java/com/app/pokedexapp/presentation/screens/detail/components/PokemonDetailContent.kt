package com.app.pokedexapp.presentation.screens.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.app.pokedexapp.domain.model.Pokemon

// Contenedor principal: columna vertical centrada, padding y alineación
@Suppress("ktlint:standard:function-naming")
@Composable
fun PokemonDetailContent(pokemon: Pokemon) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Imagen del Pokémon, cargada desde internet con Coil.
        AsyncImage(
            model = pokemon.imageUrl,
            contentDescription = pokemon.name,
            modifier = Modifier.size(200.dp),
        )

        // Separador vertical de 16dp entre la imagen y el nombre.
        Spacer(modifier = Modifier.height(16.dp))

        // // Nombre del Pokémon con estilo de título
        Text(
            text = pokemon.name,
            style = MaterialTheme.typography.headlineMedium,
        )

        // Separador menor entre el nombre y la sección de stats
        Spacer(modifier = Modifier.height(8.dp))

        // Fila con Height y Weight, distribuida equitativamente a lo ancho.
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            // Columna para Height
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Height")
                Text("${pokemon.height / 10.0}m")
            }
            // Columna para Weight
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Weight")
                Text("${pokemon.weight / 10.0}kg")
            }
        }

        // Separador entre la sección de stats y la de tipos.
        Spacer(modifier = Modifier.height(16.dp))

        // Título de la sección de tipos.
        Text("Types", style = MaterialTheme.typography.titleMedium)
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            // Fila con chips de tipos, dejando espacio fijo entre chips.
            pokemon.types.forEach { type ->
                // Composable Chip (se asume definido en el mismo paquete).
                Chip(type = type)
            }
        }
    }
}
