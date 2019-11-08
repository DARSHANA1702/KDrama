package heckfyxe.kdrama.ui.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import heckfyxe.kdrama.databinding.ItemAlbumBinding
import heckfyxe.kdrama.model.Album

class AlbumsAdapter(private val onClickListener: AlbumClickListener) :
    PagedListAdapter<Album, AlbumsAdapter.AlbumViewHolder>(DIFF) {

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Album>() {
            override fun areItemsTheSame(oldItem: Album, newItem: Album) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Album, newItem: Album) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAlbumBinding.inflate(inflater, parent, false)
        return AlbumViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    class AlbumViewHolder(
        private val binding: ItemAlbumBinding,
        private val onClickListener: AlbumClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(album: Album) {
            binding.album = album
            binding.invalidateAll()
            itemView.setOnClickListener {
                onClickListener.onClick(album)
            }
        }
    }
}