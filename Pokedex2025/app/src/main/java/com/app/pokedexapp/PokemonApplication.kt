// Clase de aplicación principal de tu proyecto.
// Su propósito es inicializar Dagger Hilt al momento de lanzar la app.
package com.app.pokedexapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Marca esta clase como la aplicación principal que inicializa Hilt.
@HiltAndroidApp
class PokemonApplication : Application()
