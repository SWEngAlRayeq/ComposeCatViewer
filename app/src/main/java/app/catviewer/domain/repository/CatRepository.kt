package app.catviewer.domain.repository

import app.catviewer.domain.model.CatImage

interface CatRepository {

    suspend fun getRandomCat(): CatImage
    fun getFavorites(): List<CatImage>
    fun saveFavorite(catImage: CatImage)
    
}