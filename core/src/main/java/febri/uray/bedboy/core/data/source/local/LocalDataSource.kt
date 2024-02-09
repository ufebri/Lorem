package febri.uray.bedboy.core.data.source.local

import febri.uray.bedboy.core.data.source.local.entity.UserEntity
import febri.uray.bedboy.core.data.source.local.room.AppDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val appDao: AppDao) {

    fun insertNewUser(userData: UserEntity) = appDao.insertNewUser(userData)

    fun getUserData(email: String, pwd: String): Flow<UserEntity> = appDao.getUser(email, pwd)

    fun getAllUser(): Flow<List<UserEntity>> = appDao.getAllUser()

    fun updateUser(user: UserEntity) = appDao.updateUser(user)

    fun deleteUser(user: UserEntity) = appDao.deleteUser(user)
}