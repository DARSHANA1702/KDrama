package heckfyxe.kdrama.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetAlbumsResponse(
    var count: Int,
    @Json(name = "items") var albums: List<Album>
)