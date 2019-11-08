package heckfyxe.kdrama.ui.videos


import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.vk.api.sdk.auth.VKAccessToken
import heckfyxe.kdrama.R
import heckfyxe.kdrama.databinding.FragmentVideoPlayerBinding


class VideoPlayerFragment : Fragment() {

    private val sharedPrefName = "com.vkontakte.android_pref_name"
    private val accessTokenName = "access_token"

    private val args: VideoPlayerFragmentArgs by navArgs()

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentVideoPlayerBinding.inflate(inflater, container, false)
        val sharedPref = context!!.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE)
        val vkAccessToken = VKAccessToken.restore(sharedPref)
        val appId = resources.getInteger(R.integer.com_vk_sdk_AppId).toString()
        val url = vkAccessToken!!.run {
            Uri.parse(args.videoLink).buildUpon()
                .appendQueryParameter("access_token", accessToken)
                .appendQueryParameter("client_id", appId)
                .appendQueryParameter("secret", secret)
                .build()
                .toString()
        }
        Log.i("VideoPlayerFragment", url)
        binding.apply {
            webView.apply {
                settings.apply {
                    javaScriptEnabled = true
                    setAppCacheEnabled(true)
                    builtInZoomControls = true
                }
                webViewClient = WebViewClient()
                webChromeClient = WebChromeClient()
                loadUrl(url)
            }

        }
        return binding.root
    }


}
