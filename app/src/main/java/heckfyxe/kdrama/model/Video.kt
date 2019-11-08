package heckfyxe.kdrama.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Video(
    var id: Long,
    var title: String,
    var duration: Int,
    var description: String,
    @Json(name = "photo_130") var photoSmall: String?,
    @Json(name = "photo_320") var photoMedium: String?,
    @Json(name = "photo_800") var photoLarge: String?,
    var player: String
)