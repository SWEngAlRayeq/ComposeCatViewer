package app.catviewer.presentation.ui.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.catviewer.presentation.ui.cats.CatViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: CatViewModel = hiltViewModel()
) {
    val favorites = viewModel.favorites

    if (favorites.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xFFFDF8FF)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No favorite cats yet 🐈🐾",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray
            )
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xFFFDF8FF))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {
            items(favorites) { cat ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .shadow(
                            elevation = 6.dp,
                            shape = RoundedCornerShape(16.dp),
                            ambientColor = Color.Black.copy(alpha = 0.08f),
                            spotColor = Color.Black.copy(alpha = 0.08f)
                        ),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(cat.url)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Favorite Cat",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(16.dp))
                    )
                }
            }
        }
    }
}
