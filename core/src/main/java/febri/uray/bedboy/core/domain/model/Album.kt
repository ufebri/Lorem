package febri.uray.bedboy.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album(
    var albumId: Int, var id: Int, var title: String, var url: String, var thumbnailUrl: String
) : Parcelable
