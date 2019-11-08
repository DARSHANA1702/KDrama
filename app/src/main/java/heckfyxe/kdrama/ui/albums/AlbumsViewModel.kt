package heckfyxe.kdrama.ui.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import heckfyxe.kdrama.model.Album
import heckfyxe.kdrama.repository.AlbumsRepository
import javax.inject.Inject

class AlbumClickListener(private val action: (Album) -> Unit) {
    fun onClick(album: Album) = action(album)
}

class AlbumsViewModel @Inject constructor(private val repository: AlbumsRepository) : ViewModel() {
    val albums = repository.pagedList

    val errors: LiveData<Exception> = Transformations.map(repository.errors) { it }

    private val _navigateToVideosFragment = MutableLiveData<Album?>()
    val navigateToVideosFragment: LiveData<Album?> = _navigateToVideosFragment

    fun onAlbumClicked(album: Album) {
        _navigateToVideosFragment.value = album
    }

    fun navigatedToVideosFragment() {
        _navigateToVideosFragment.value = null
    }

    override fun onCleared() {
        super.onCleared()

        repository.clear()
    }
}
