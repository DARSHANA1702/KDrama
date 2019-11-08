package heckfyxe.kdrama.repository

import heckfyxe.kdrama.network.NetworkManager
import javax.inject.Inject

class VideosRepository @Inject constructor(private val networkManager: NetworkManager) {
    suspend fun getVideos(albumId: Int) =
        networkManager.getVideos(albumId).sortedBy { it.id }
}