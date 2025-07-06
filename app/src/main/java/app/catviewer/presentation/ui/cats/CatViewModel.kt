package app.catviewer.presentation.ui.cats

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.catviewer.domain.model.CatImage
import app.catviewer.domain.usecase.GetRandomCatUseCase
import app.catviewer.domain.usecase.SaveFavCatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    private val getRandomCatUseCase: GetRandomCatUseCase,
    private val saveFavCatUseCase: SaveFavCatUseCase
) : ViewModel() {


    var catImage by mutableStateOf<CatImage?>(null)
        private set

    var favorites = mutableStateListOf<CatImage>()
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun loadRandomCategory() {
        isLoading = true
        viewModelScope.launch {
            catImage = getRandomCatUseCase()
            isLoading = false
        }
    }

    fun saveFavorite() {
        catImage?.let {
            saveFavCatUseCase(it)
            favorites.add(it)
        }
    }

}