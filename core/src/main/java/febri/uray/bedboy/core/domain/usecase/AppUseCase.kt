package febri.uray.bedboy.core.domain.usecase

import androidx.paging.PagingData
import febri.uray.bedboy.core.domain.model.Album
import febri.uray.bedboy.core.domain.model.User
import kotlinx.coroutines.flow.Flow


interface AppUseCase {

    fun insertNewUser(user: User)
    fun getUser(email: String, pwd: String): Flow<User>
    fun getAllAlbum(): Flow<PagingData<Album>>
    fun getAllUser(): Flow<List<User>>
    fun updateUser(user: User)
    fun deleteUser(user: User)
}