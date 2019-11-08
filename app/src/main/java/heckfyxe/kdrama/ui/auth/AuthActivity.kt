package heckfyxe.kdrama.ui.auth


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope


class AuthActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ERROR_CODE = "heckfyxe.kdrama.ui.auth.EXTRA_ERROR_CODE"
        const val EXTRA_IS_FAILED = "heckfyxe.kdrama.ui.auth.EXTRA_IS_FAILED"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!VK.isLoggedIn())
            VK.login(this, arrayListOf(VKScope.VIDEO))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object: VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                setResult(RESULT_OK)
                finish()
            }

            override fun onLoginFailed(errorCode: Int) {
                setResult(RESULT_CANCELED, Intent().apply {
                    putExtra(EXTRA_IS_FAILED, true)
                    putExtra(EXTRA_ERROR_CODE, errorCode)
                })
                finish()
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
