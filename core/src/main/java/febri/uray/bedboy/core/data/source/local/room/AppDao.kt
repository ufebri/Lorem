package febri.uray.bedboy.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import febri.uray.bedboy.core.data.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewUser(entity: UserEntity)

    @Query("SELECT * FROM user_entity WHERE user_email = :email AND user_password = :pwd")
    fun getUser(email: String, pwd: String): Flow<UserEntity>

    @Query("SELECT * FROM user_entity ORDER BY user_id ASC")
    fun getAllUser(): Flow<List<UserEntity>>

    @Update
    fun updateUser(mUser: UserEntity)

    @Delete
    fun deleteUser(mUser: UserEntity)
}