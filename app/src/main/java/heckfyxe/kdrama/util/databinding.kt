package heckfyxe.kdrama.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import heckfyxe.kdrama.model.Album

@BindingAdapter("loadAlbumImage")
fun loadAlbumImage(imageView: ImageView?, album: Album?) {
    imageView ?: return
    val thumbnail = Glide.with(imageView).load(album?.photoSmall)
    Glide.with(imageView)
        .load(album?.photoLarge)
        .thumbnail(thumbnail)
        .into(imageView)
}