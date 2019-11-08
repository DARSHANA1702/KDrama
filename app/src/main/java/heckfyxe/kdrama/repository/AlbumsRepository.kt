package heckfyxe.kdrama.repository

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import heckfyxe.kdrama.model.Album
import heckfyxe.kdrama.repository.datasource.AlbumsDataSource
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class AlbumsRepository @Inject constructor(private val factory: AlbumsDataSource.Factory) {

    companion object {
        private const val PAGE_SIZE = 18
    }

    val errors = factory.errors

    val pagedList = LivePagedListBuilder<Int, Album>(
        factory,
        PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(PAGE_SIZE)
            .build()
    ).build()

    fun clear() {
        factory.clear()
    }
}