package app.catviewer.data.api

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

interface CatApiService {

    @GET("V1/images/search")
    suspend fun getRandomCat(): List<CatResponse>

    data class CatResponse(
        @SerializedName("url")
        val url: String
    )
}