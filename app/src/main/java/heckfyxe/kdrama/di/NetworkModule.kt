package heckfyxe.kdrama.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import heckfyxe.kdrama.util.AlbumTitleAdapter
import heckfyxe.kdrama.util.io
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(AlbumTitleAdapter())
            .build()
}