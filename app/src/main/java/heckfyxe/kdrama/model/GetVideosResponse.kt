package heckfyxe.kdrama.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetVideosResponse(
    var count: Int,
    var items: List<Video>
)