package heckfyxe.kdrama.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import heckfyxe.kdrama.ui.videos.VideosViewModel

@Module
abstract class VideosModule {

    @Binds
    @IntoMap
    @ViewModelKey(VideosViewModel::class)
    abstract fun videosViewModel(viewModel: VideosViewModel): ViewModel
}