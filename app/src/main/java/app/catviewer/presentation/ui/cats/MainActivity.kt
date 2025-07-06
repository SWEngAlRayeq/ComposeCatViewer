package app.catviewer.presentation.ui.cats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import app.catviewer.presentation.ui.favorites.FavoriteScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            var currentScreen by remember { mutableStateOf("main") }

            MaterialTheme(
                colorScheme = lightColorScheme(
                    background = Color(0xFFFDF6F9),
                    primary = Color(0xFF7C4DFF),
                    secondary = Color(0xFFFF4081),
                )
            ) {
                Scaffold(
                    containerColor = MaterialTheme.colorScheme.background,
                    bottomBar = {
                        NavigationBar(
                            containerColor = Color.White.copy(alpha = 0.95f),
                            tonalElevation = 8.dp
                        ) {
                            NavigationBarItem(
                                selected = currentScreen == "main",
                                onClick = { currentScreen = "main" },
                                icon = {
                                    Text(
                                        text = "🐱",
                                        fontSize = 22.sp
                                    )
                                },
                                label = { Text("cats") }
                            )
                            NavigationBarItem(
                                selected = currentScreen == "favorites",
                                onClick = { currentScreen = "favorites" },
                                icon = {
                                    Text(
                                        text = "❤️",
                                        fontSize = 22.sp
                                    )
                                },
                                label = { Text("favorites") }
                            )
                        }
                    }
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        when (currentScreen) {
                            "main" -> MainScreen(modifier = Modifier.padding(16.dp))
                            "favorites" -> FavoriteScreen(modifier = Modifier.padding(16.dp))
                        }
                    }
                }
            }
        }
    }
}
