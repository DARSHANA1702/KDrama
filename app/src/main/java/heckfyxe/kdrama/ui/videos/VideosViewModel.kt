package heckfyxe.kdrama.ui.videos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import heckfyxe.kdrama.model.Video
import heckfyxe.kdrama.repository.VideosRepository
import heckfyxe.kdrama.util.io
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class VideoClickListener(private val action: (Video) -> Unit) {
    fun onClick(video: Video) = action(video)
}

class VideosViewModel @Inject constructor(
    private val repository: VideosRepository) : ViewModel() {

    private val _videos = MutableLiveData<List<Video>>()
    val videos : LiveData<List<Video>> = _videos

    private val _errors = MutableLiveData<Exception>()
    val errors : LiveData<Exception> = _errors

    private val _navigateToVideoPlayer = MutableLiveData<Video?>()
    val navigateToVideoPlayer: LiveData<Video?> = _navigateToVideoPlayer

    fun loadVideos(albumId: Int) {
        if (_videos.value != null) return
        viewModelScope.launch {
            try {
                val videos = withContext(io) { repository.getVideos(albumId) }
                _videos.value = videos
            } catch (e: Exception) {
                _errors.value = e
            }
        }
    }

    fun playVideo(video: Video) {
        _navigateToVideoPlayer.value = video
    }

    fun onVideoPlayerNavigated() {
        _navigateToVideoPlayer.value = null
    }
}
