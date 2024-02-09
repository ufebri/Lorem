package febri.uray.bedboy.core.data.source.remote.network

import febri.uray.bedboy.core.data.source.remote.response.DataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("photos")
    suspend fun getAllAlbum(
        @Query("_page") page: Int = 1,
        @Query("_limit") limit: Int = 10
    ): List<DataResponse>
}