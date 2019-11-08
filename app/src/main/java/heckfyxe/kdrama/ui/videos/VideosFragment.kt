package heckfyxe.kdrama.ui.videos

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import heckfyxe.kdrama.KDramaApplication
import heckfyxe.kdrama.databinding.FragmentVideosBinding
import javax.inject.Inject

class VideosFragment : Fragment() {

    private val args: VideosFragmentArgs by navArgs()

    private lateinit var binding: FragmentVideosBinding
    private lateinit var adapter: VideosAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: VideosViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity!!.application as KDramaApplication).appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = VideosAdapter(VideoClickListener {
            viewModel.playVideo(it)
        })

        viewModel.loadVideos(args.albumId)
        observeViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideosBinding.inflate(inflater, container, false)
        binding.apply {
            val itemDecoration = DividerItemDecoration(context!!, RecyclerView.VERTICAL)
            videosList.addItemDecoration(itemDecoration)
            videosList.adapter = adapter

            (activity as AppCompatActivity).setSupportActionBar(toolbar)
            albumTitle = args.albumTitle
            executePendingBindings()
        }
        return binding.root
    }

    private fun observeViewModel() {
        viewModel.videos.observe(this, Observer {
            Log.i("Videos", it.toString())
            adapter.submitList(it)
        })

        viewModel.navigateToVideoPlayer.observe(this, Observer {
            it ?: return@Observer

            viewModel.onVideoPlayerNavigated()
            findNavController().navigate(
                VideosFragmentDirections.actionVideosFragmentToVideoPlayerFragment(
                    it.player
                )
            )
        })

        viewModel.errors.observe(this, Observer {
            Log.e("VideosFragment", "VK error", it)
        })
    }
}
