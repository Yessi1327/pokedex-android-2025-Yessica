// Indica el paquete al que pertenece el archivo
package com.app.pokedexapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.pokedexapp.presentation.navigation.PokemonNavGraph
import com.app.pokedexapp.presentation.theme.PokedexAppTheme

// Clase principal que representa la pantalla base de la app.
class MainActivity : ComponentActivity() {
    // Se ejecuta cuando la Activity se crea
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Habilita el modo edge-to-edge:
        enableEdgeToEdge()

        // Todo lo que esté dentro de setContent será lo que se renderice en pantalla.
        setContent {
            // Aplica el tema global de la app, definido en PokedexAppTheme.
            // Aquí se controlan los colores, tipografías y estilos visuales.
            PokedexAppTheme {
                // Scaffold crea una estructura básica para la pantalla.
                // fillMaxSize() hace que ocupe todo el tamaño disponible.
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    // Dentro del Scaffold se coloca el contenido principal:
                    // El gráfico de navegación que se encarga de mostrar las pantallas (Home, Detail, etc.)
                    PokemonNavGraph()
                }
            }
        }
    }
}
