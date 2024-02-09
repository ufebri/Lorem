package febri.uray.bedboy.core.data.source.remote

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import febri.uray.bedboy.core.data.source.remote.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    @ApplicationContext private val context: Context
) {
    val TAG = "RemoteDataSource"
    val client = apiService
}

