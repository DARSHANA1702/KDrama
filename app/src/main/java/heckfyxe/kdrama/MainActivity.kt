package heckfyxe.kdrama

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKTokenExpiredHandler
import heckfyxe.kdrama.util.toast

class MainActivity : AppCompatActivity() {

    private val tokenTracker = object: VKTokenExpiredHandler {
        override fun onTokenExpired() {
            if (isOnCreateCompleted) {
                navigateToStartActivity()
            } else {
                isTokenExpired = true
            }
        }
    }

    private var isTokenExpired = false
    private var isOnCreateCompleted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (isTokenExpired) {
            navigateToStartActivity()
            return
        }

        isOnCreateCompleted = true

        VK.addTokenExpiredHandler(tokenTracker)
    }

    override fun onDestroy() {
        super.onDestroy()

        VK.removeTokenExpiredHandler(tokenTracker)
    }

    private fun navigateToStartActivity() {
        findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_startFragment)
        toast(R.string.token_expired)
    }
}
