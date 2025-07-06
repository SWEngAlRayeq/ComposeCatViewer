package app.catviewer.di

import app.catviewer.data.api.CatApiService
import app.catviewer.data.repository.CatRepoImpl
import app.catviewer.domain.repository.CatRepository
import app.catviewer.domain.usecase.GetRandomCatUseCase
import app.catviewer.domain.usecase.SaveFavCatUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideCatApi(): CatApiService = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CatApiService::class.java)

    @Provides
    fun provideRepository(api: CatApiService): CatRepository = CatRepoImpl(api)

    @Provides
    fun provideGetCatUseCase(repo: CatRepository) = GetRandomCatUseCase(repo)

    @Provides
    fun provideSaveFavoriteCatUseCase(repo: CatRepository) = SaveFavCatUseCase(repo)

}