package heckfyxe.kdrama.ui.albums

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import heckfyxe.kdrama.KDramaApplication
import heckfyxe.kdrama.R
import heckfyxe.kdrama.databinding.FragmentAlbumsBinding
import heckfyxe.kdrama.model.Album
import heckfyxe.kdrama.util.toast
import javax.inject.Inject

class AlbumsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: AlbumsViewModel by viewModels { viewModelFactory }

    private lateinit var adapter: AlbumsAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity!!.application as KDramaApplication).appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = AlbumsAdapter(AlbumClickListener {
            viewModel.onAlbumClicked(it)
        })
        observeViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentAlbumsBinding.inflate(inflater).run {
        albumsRecyclerView.adapter = adapter
        root
    }

    private fun observeViewModel() {
        viewModel.albums.observe(this, Observer {
            adapter.submitList(it)
        })

        viewModel.navigateToVideosFragment.observe(this, Observer { album: Album? ->
            album ?: return@Observer
            viewModel.navigatedToVideosFragment()
            val direction = AlbumsFragmentDirections.actionAlbumsFragmentToVideosFragment(
                album.id!!,
                album.title!!
            )
            findNavController().navigate(direction)
        })

        viewModel.errors.observe(this, Observer {
            Log.e("getAlbums", it.toString())
            toast(R.string.loading_error)
        })
    }
}
