package com.app.pokedexapp.domain.model

// clase especialmente diseñada para mantener datos.
data class Pokemon(
    val id: String,
    val name: String,
    val imageUrl: String,
    val weight: Int,
    val height: Int,
    val types: List<String>,
) {
    // El companion object es la forma que tiene Kotlin de implementar
    // el patrón Singleton y métodos/propiedades estáticas
    companion object {
        // función temporal, pues solo la usaremos en este laboratorio
        // para simular algunos Pokemon y poder pintarlos en pantalla.
        fun getMockData(): List<Pokemon> =
            listOf(
                Pokemon(
                    id = "1",
                    name = "Bulbasaur",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                    weight = 69,
                    height = 7,
                    types = listOf("grass", "poison"),
                ),
                Pokemon(
                    id = "2",
                    name = "Ivysaur",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png",
                    weight = 130,
                    height = 10,
                    types = listOf("grass", "poison"),
                ),
                // Agregar más Pokémon mock...
            )
    }
}
