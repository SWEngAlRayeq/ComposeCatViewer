package app.catviewer.data.repository

import app.catviewer.data.api.CatApiService
import app.catviewer.domain.model.CatImage
import app.catviewer.domain.repository.CatRepository

class CatRepoImpl(
    private val api: CatApiService
) : CatRepository {

    private val favorites = mutableListOf<CatImage>()

    override suspend fun getRandomCat(): CatImage {
        val response = api.getRandomCat()
        return CatImage(response.first().url)
    }

    override fun getFavorites(): List<CatImage> = favorites

    override fun saveFavorite(catImage: CatImage) {
        if (!favorites.contains(catImage)) favorites.add(catImage)
    }
}