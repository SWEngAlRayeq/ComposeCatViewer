package app.catviewer.domain.usecase

import app.catviewer.domain.repository.CatRepository

class GetRandomCatUseCase(
    private val catRepository: CatRepository
) {
    suspend operator fun invoke() = catRepository.getRandomCat()
}