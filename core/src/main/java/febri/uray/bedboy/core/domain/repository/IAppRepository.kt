package febri.uray.bedboy.core.domain.repository

import androidx.paging.PagingData
import febri.uray.bedboy.core.domain.model.Album
import febri.uray.bedboy.core.domain.model.User
import kotlinx.coroutines.flow.Flow


interface IAppRepository {

    fun insertNewUser(newUser: User)

    fun getUser(email: String, pwd: String): Flow<User>

    fun getAllAlbum(): Flow<PagingData<Album>>

    fun getAllUser(): Flow<List<User>>

    fun updateUser(user: User)

    fun deleteUser(user: User)
}