package heckfyxe.kdrama.di

import dagger.Component
import heckfyxe.kdrama.ui.albums.AlbumsFragment
import heckfyxe.kdrama.ui.videos.VideosFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class,
    AlbumsModule::class,
    VideosModule::class,
    ViewModelModule::class
])
interface AppComponent {
    fun inject(fragment: AlbumsFragment)
    fun inject(fragment: VideosFragment)
}