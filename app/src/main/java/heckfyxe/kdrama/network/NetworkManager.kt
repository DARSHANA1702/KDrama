package heckfyxe.kdrama.network

import com.squareup.moshi.Moshi
import com.vk.api.sdk.internal.await
import heckfyxe.kdrama.model.Album
import heckfyxe.kdrama.model.GetAlbumsResponse
import heckfyxe.kdrama.model.Video
import javax.inject.Inject

class NetworkManager @Inject constructor(private val moshi: Moshi) {
    suspend fun getAlbumsResponse(offset: Int, count: Int): GetAlbumsResponse =
        GetAlbumsVKRequest(moshi, offset, count).await()

    suspend fun getAlbums(offset: Int, count: Int): List<Album> =
        GetAlbumsVKRequest(moshi, offset, count).await().albums

    suspend fun getVideos(albumId: Int): List<Video> =
        GetVideosVKRequest(moshi, albumId).await().items
}