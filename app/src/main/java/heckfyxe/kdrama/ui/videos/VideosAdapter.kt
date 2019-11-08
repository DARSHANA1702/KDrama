package heckfyxe.kdrama.ui.videos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import heckfyxe.kdrama.databinding.ItemVideoBinding
import heckfyxe.kdrama.model.Video

class VideosAdapter(private val videoClickListener: VideoClickListener) :
    ListAdapter<Video, VideosAdapter.ViewHolder>(VideoDiffItemCallback()) {

    class VideoDiffItemCallback : DiffUtil.ItemCallback<Video>() {
        override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context!!)
        val binding = ItemVideoBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, videoClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    class ViewHolder(
        private val binding: ItemVideoBinding,
        private val videoClickListener: VideoClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(video: Video) {
            binding.video = video
            binding.executePendingBindings()

            itemView.setOnClickListener {
                videoClickListener.onClick(video)
            }
        }
    }
}