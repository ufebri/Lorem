package febri.uray.bedboy.core.domain.usecase

import androidx.paging.PagingData
import febri.uray.bedboy.core.domain.model.Album
import febri.uray.bedboy.core.domain.model.User
import febri.uray.bedboy.core.domain.repository.IAppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppInteractor @Inject constructor(private var appRepository: IAppRepository) :
    AppUseCase {

    override fun insertNewUser(user: User) = appRepository.insertNewUser(user)

    override fun getUser(email: String, pwd: String): Flow<User> = appRepository.getUser(email, pwd)

    override fun getAllAlbum(): Flow<PagingData<Album>> = appRepository.getAllAlbum()
    override fun getAllUser(): Flow<List<User>> = appRepository.getAllUser()

    override fun updateUser(user: User) = appRepository.updateUser(user)

    override fun deleteUser(user: User) = appRepository.deleteUser(user)

}