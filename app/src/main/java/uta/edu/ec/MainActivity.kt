package uta.edu.ec

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uta.edu.ec.ui.theme.DevicesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DevicesTheme {
                var devices by remember { mutableStateOf(listOf<Device>()) }
                var isLoading by remember { mutableStateOf(true) }

                // LaunchedEffect asegura que la petición solo se haga al iniciar la App
                LaunchedEffect(Unit) {
                    getDevices { result ->
                        devices = result
                        isLoading = false // Ocultamos el cargando
                    }
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.fillMaxSize()) {
                        // Pasamos el padding al MainView
                        MainView(Modifier.padding(innerPadding), devices = devices)

                        if (isLoading) {
                            // Centramos el indicador de carga
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }
                }
            }
        }
    }

    private fun getDevices(onResult: (List<Device>) -> Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(DeviceService::class.java)

        lifecycleScope.launch {
            try {
                // Eliminamos el cancel() que bloqueaba todo
                val result = service.getAllDevices()
                onResult(result)
            } catch (e: Exception) {
                // En caso de error, devolvemos una lista vacía para evitar el crash
                onResult(emptyList())
            }
        }
    }
}
