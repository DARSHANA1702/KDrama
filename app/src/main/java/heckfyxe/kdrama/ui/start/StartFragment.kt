package heckfyxe.kdrama.ui.start


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.vk.api.sdk.VK
import heckfyxe.kdrama.R
import heckfyxe.kdrama.ui.auth.AuthActivity
import heckfyxe.kdrama.util.longSnackbar


class StartFragment : Fragment() {

    companion object {
        private const val RC_AUTH = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (VK.isLoggedIn()) {
            findNavController().navigate(R.id.action_startFragment_to_albumsFragment)
        } else {
            startActivityForResult(Intent(context!!, AuthActivity::class.java), RC_AUTH)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            RC_AUTH -> {
                if (VK.isLoggedIn()) {
                    findNavController().navigate(R.id.action_startFragment_to_albumsFragment)
                } else {
                    longSnackbar(R.string.sign_in_error)
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
