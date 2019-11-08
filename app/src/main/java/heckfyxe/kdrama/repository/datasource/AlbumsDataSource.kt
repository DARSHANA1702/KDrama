package heckfyxe.kdrama.repository.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PositionalDataSource
import heckfyxe.kdrama.model.Album
import heckfyxe.kdrama.network.NetworkManager
import heckfyxe.kdrama.util.io
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class AlbumsDataSource(
    private val networkManager: NetworkManager
) : PositionalDataSource<Album>() {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(job + io)
    private val _errors = MutableLiveData<Exception>()
    val errors: LiveData<Exception> = _errors

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Album>) {
        scope.launch(io) {
            try {
                val response = networkManager.getAlbumsResponse(
                    params.requestedStartPosition,
                    params.requestedLoadSize
                )
                callback.onResult(response.albums, params.requestedStartPosition, response.count)
            } catch (e: Exception) {
                _errors.postValue(e)
            }
        }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Album>) {
        scope.launch(io) {
            try {
                val albums = networkManager.getAlbums(params.startPosition, params.loadSize)
                callback.onResult(albums)
            } catch (e: Exception) {
                _errors.postValue(e)
            }
        }
    }

    fun clear() {
        job.cancel()
    }

    class Factory @Inject constructor(
        private val networkManager: NetworkManager
    ) : DataSource.Factory<Int, Album>() {

        var dataSource: AlbumsDataSource? = null
        val errors: LiveData<Exception>
            get() = dataSource?.errors ?: MutableLiveData<Exception>()


        override fun create(): DataSource<Int, Album> {
            clear()
            dataSource = AlbumsDataSource(networkManager)
            return dataSource!!
        }

        fun clear() {
            dataSource?.clear()
        }
    }
}