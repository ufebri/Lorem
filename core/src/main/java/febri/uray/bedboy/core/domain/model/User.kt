package febri.uray.bedboy.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var userID: Long? = null,
    var userUsername: String,
    var userEmail: String,
    var userPassword: String,
    var userRole: String
) : Parcelable
