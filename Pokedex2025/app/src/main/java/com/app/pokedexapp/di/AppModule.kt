package com.app.pokedexapp.di

import com.app.pokedexapp.data.remote.api.PokemonApi
import com.app.pokedexapp.data.repository.PokemonRepositoryImpl
import com.app.pokedexapp.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// di/AppModule.kt

// Module: Indica que esta clase provee dependencias
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
            .Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providePokemonApi(retrofit: Retrofit): PokemonApi = retrofit.create(PokemonApi::class.java)

    @Provides
    @Singleton
    fun providePokemonRepository(api: PokemonApi): PokemonRepository = PokemonRepositoryImpl(api)
}
