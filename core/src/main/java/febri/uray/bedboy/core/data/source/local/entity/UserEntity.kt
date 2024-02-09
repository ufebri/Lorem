package febri.uray.bedboy.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_entity")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("user_id")
    var userID: Long? = null,

    @ColumnInfo("user_username")
    var userUsername: String,

    @ColumnInfo("user_email")
    var userEmail: String,

    @ColumnInfo("user_password")
    var userPassword: String,

    @ColumnInfo("user_role")
    var userRole: String
)
