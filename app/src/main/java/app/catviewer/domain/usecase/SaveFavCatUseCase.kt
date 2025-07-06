package app.catviewer.domain.usecase

import app.catviewer.domain.model.CatImage
import app.catviewer.domain.repository.CatRepository

class SaveFavCatUseCase(
    private val repository: CatRepository
) {
    operator fun invoke(catImage: CatImage) =
        repository.saveFavorite(catImage)
}