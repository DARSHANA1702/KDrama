package heckfyxe.kdrama.util

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import heckfyxe.kdrama.model.AlbumTitle

class AlbumTitleAdapter {
    @FromJson
    @AlbumTitle
    fun fromJson(title: String): String =
        title.replace("[Озвучка SoftBox]", "", ignoreCase = true)

    @ToJson
    fun toJson(@AlbumTitle title: String) = title
}