package heckfyxe.kdrama

import android.app.Application
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiConfig
import heckfyxe.kdrama.di.AppComponent
import heckfyxe.kdrama.di.DaggerAppComponent

class KDramaApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.create()
    }
}