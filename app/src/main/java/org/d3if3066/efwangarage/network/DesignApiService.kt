package org.d3if3066.efwangarage.network


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3066.efwangarage.model.Design
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/mhdanandaa/cat-API/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface DesignApiService {
    @GET("design-api.json")
    suspend fun getDesign() : List<Design>
}

object DesignApi {
    val service: DesignApiService by lazy {
        retrofit.create(DesignApiService::class.java)
    }

    fun getDesignUrl(imageId: String): String {
        return "$BASE_URL$imageId"
    }

}
enum class ApiStatus{LOADING, SUCCESS, FAILED}
