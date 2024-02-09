package febri.uray.bedboy.core.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import febri.uray.bedboy.core.data.source.local.LocalDataSource
import febri.uray.bedboy.core.data.source.remote.AppPagingSource
import febri.uray.bedboy.core.data.source.remote.RemoteDataSource
import febri.uray.bedboy.core.domain.model.Album
import febri.uray.bedboy.core.domain.model.User
import febri.uray.bedboy.core.domain.repository.IAppRepository
import febri.uray.bedboy.core.util.AppExecutors
import febri.uray.bedboy.core.util.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IAppRepository {

    override fun insertNewUser(newUser: User) {
        return appExecutors.diskIO()
            .execute { localDataSource.insertNewUser(DataMapper.mapDomainToUserEntities(newUser)) }
    }

    override fun getUser(email: String, pwd: String): Flow<User> {
        return localDataSource.getUserData(email, pwd).map {
            DataMapper.mapEntitiesToUserDomain(it)
        }
    }

    override fun getAllAlbum(): Flow<PagingData<Album>> {
        return Pager(
            config = PagingConfig(10),
            pagingSourceFactory = {
                AppPagingSource(remoteDataSource.client)
            }
        ).flow
    }

    override fun getAllUser(): Flow<List<User>> {
        return localDataSource.getAllUser().map { DataMapper.mapEntitiesToUsersDomain(it) }
    }

    override fun updateUser(user: User) {
        val mUser = DataMapper.mapDomainToUserEntities(user)
        return appExecutors.diskIO().execute { localDataSource.updateUser(mUser) }
    }

    override fun deleteUser(user: User) {
        val mUser = DataMapper.mapDomainToUserEntities(user)
        return appExecutors.diskIO().execute { localDataSource.deleteUser(mUser) }
    }

}