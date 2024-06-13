package org.d3if3066.efwangarage.network


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if3066.efwangarage.model.Design
import org.d3if3066.efwangarage.model.OpStatus
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

private const val BASE_URL = "https://unspoken.my.id/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface DesignApiService {
    @GET("api_fadil.php")
    suspend fun getDesign(
        @Header("Authorization") userId: String
    ) : List<Design>

    @Multipart
    @POST("api_fadil.php")
    suspend fun postDesign(
        @Header("Authorization") userId: String,
        @Part("nama") nama: RequestBody,
        @Part("jenis") jenis:RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus

    @DELETE("api_fadil.php")
    suspend fun deleteDesign(
        @Header("Authorization") userId: String,
        @Query("id") id: String
    ): OpStatus
}

object DesignApi {
    val service: DesignApiService by lazy {
        retrofit.create(DesignApiService::class.java)
    }

    fun getDesignUrl(imageId: String): String {
        return "${BASE_URL}image.php?id=$imageId"
    }

}
enum class ApiStatus{LOADING, SUCCESS, FAILED}
