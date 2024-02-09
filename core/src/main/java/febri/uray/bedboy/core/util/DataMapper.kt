package febri.uray.bedboy.core.util

import febri.uray.bedboy.core.data.source.local.entity.UserEntity
import febri.uray.bedboy.core.data.source.remote.response.DataResponse
import febri.uray.bedboy.core.domain.model.Album
import febri.uray.bedboy.core.domain.model.User

object DataMapper {

    fun mapDomainToUserEntities(input: User): UserEntity = input.let {
        UserEntity(
            userID = it.userID,
            userUsername = it.userUsername,
            userEmail = it.userEmail,
            userPassword = it.userPassword,
            userRole = it.userRole
        )
    }

    fun mapEntitiesToUserDomain(input: UserEntity?): User = input.let {
        User(
            it?.userID ?: -1,
            it?.userUsername ?: "",
            it?.userEmail ?: "",
            it?.userPassword ?: "",
            it?.userRole ?: ""
        )
    }

    fun mapEntitiesToUsersDomain(input: List<UserEntity>): List<User> {
        val mListData = ArrayList<User>()
        input.map {
            val user =
                User(it.userID ?: -1, it.userUsername, it.userEmail, it.userPassword, it.userRole)
            mListData.add(user)
        }
        return mListData
    }

    fun mapResponseToAlbumDomain(input: List<DataResponse>): List<Album> {
        val mListData = ArrayList<Album>()
        input.map {
            val album = Album(it.albumId, it.id, it.title, it.url, it.thumbnailUrl)
            mListData.add(album)
        }
        return mListData
    }
}