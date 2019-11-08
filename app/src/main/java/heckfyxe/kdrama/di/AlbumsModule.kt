package heckfyxe.kdrama.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import heckfyxe.kdrama.ui.albums.AlbumsViewModel

@Module
abstract class AlbumsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AlbumsViewModel::class)
    abstract fun albumsViewModel(viewModel: AlbumsViewModel): ViewModel
}