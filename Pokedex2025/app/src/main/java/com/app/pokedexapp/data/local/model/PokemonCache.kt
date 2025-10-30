// haremos nuestra propia estructura para facilitar el manejo que tendremos local
package com.app.pokedexapp.data.local.model

import com.app.pokedexapp.domain.model.Pokemon

data class PokemonCache(
    val pokemonList: List<Pokemon>,
    val lastUpdate: Long,
    val offset: Int,
    val totalCount: Int,
)
