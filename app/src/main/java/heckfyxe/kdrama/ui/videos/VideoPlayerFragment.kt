package heckfyxe.kdrama.ui.videos


import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.webkit.CookieManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.vk.api.sdk.auth.VKAccessToken
import heckfyxe.kdrama.databinding.FragmentVideoPlayerBinding


class VideoPlayerFragment : Fragment() {

    private val sharedPrefName = "com.vkontakte.android_pref_name"

    private val args: VideoPlayerFragmentArgs by navArgs()

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentVideoPlayerBinding.inflate(inflater).run {
        val sharedPref = context!!.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE)
        val vkAccessToken = VKAccessToken.restore(sharedPref)

        val cookieManager = CookieManager.getInstance()
        cookieManager.setCookie(args.videoLink, vkAccessToken?.secret ?: "")
        webView.apply {
            settings.apply {
                javaScriptEnabled = true
                setAppCacheEnabled(true)
            }
            loadUrl(args.videoLink)
        }

        root
    }

    override fun onStart() {
        super.onStart()

        activity?.window?.decorView?.systemUiVisibility =
            SYSTEM_UI_FLAG_FULLSCREEN or SYSTEM_UI_FLAG_HIDE_NAVIGATION or SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
    }

    override fun onDestroy() {
        super.onDestroy()

        activity?.window?.decorView?.systemUiVisibility = SYSTEM_UI_FLAG_VISIBLE
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
    }
}
