package heckfyxe.kdrama.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.JsonQualifier

@JsonClass(generateAdapter = true)
data class Album(
    var id: Int?,
    var count: Int?,
    @AlbumTitle var title: String?,
    @Json(name = "photo_160") var photoSmall: String?,
    @Json(name = "photo_320") var photoLarge: String?
)

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class AlbumTitle