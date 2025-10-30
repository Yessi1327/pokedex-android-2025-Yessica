// Este archivo define un módulo de dependencias para Hilt
package com.app.pokedexapp.di

// Tuve que cambiar todos las Jakarta tanto de inject como de Singleton
import android.content.Context
import com.app.pokedexapp.data.local.preferences.PokemonPreferences
import com.app.pokedexapp.data.remote.api.PokemonApi
import com.app.pokedexapp.data.repository.PokemonRepositoryImpl
import com.app.pokedexapp.domain.model.Pokemon
import com.app.pokedexapp.domain.repository.PokemonRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// di/AppModule.kt

// Module: Indica que esta clase provee dependencias
// es decir, las dependencias que cree estarán disponibles durante toda la vida de la app.
@Module
// @InstallIn(SingletonComponent::class): Las dependencias viven durante toda la app
@InstallIn(SingletonComponent::class)
object AppModule {
    // @Provides: Método que crea una dependencia
    @Provides
    // @Singleton: Solo se crea una instancia
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit
            // Crea un constructor para configurar Retrofit.
            .Builder()
            // URL base de la API de Pokémon.
            .baseUrl("https://pokeapi.co/api/v2/")
            // Usa Gson para convertir JSON a objetos.
            .addConverterFactory(GsonConverterFactory.create())
            // Finalmente, construye la instancia de Retrofit.
            .build()

    // GSON es una librería externa, recuerda que todas las librerías externas al proyecto
    // no manejan la inyección por default
    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    // Solo existirá una instancia compartida en toda la app.
    // Usa Retrofit para crear una implementación automática de la interfaz PokemonApi.
    @Provides
    @Singleton
    fun providePokemonApi(retrofit: Retrofit): PokemonApi = retrofit.create(PokemonApi::class.java)

    @Provides
    @Singleton
    fun providePokemonPreferences(
        @ApplicationContext context: Context,
        gson: Gson,
    ): PokemonPreferences = PokemonPreferences(context, gson)

    // Retorna una implementación concreta del repositorio, usando la API.
    @Provides
    @Singleton
    fun providePokemonRepository(
        api: PokemonApi,
        preferences: PokemonPreferences,
    ): PokemonRepository = PokemonRepositoryImpl(api, preferences)
}
