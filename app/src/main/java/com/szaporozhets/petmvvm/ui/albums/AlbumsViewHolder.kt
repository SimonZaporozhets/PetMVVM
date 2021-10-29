package com.szaporozhets.petmvvm.ui.albums

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.szaporozhets.petmvvm.data.entities.Album
import com.szaporozhets.petmvvm.databinding.ItemAlbumBinding

class AlbumsViewHolder(
    private val itemBinding: ItemAlbumBinding,
    private val listener: AlbumsItemListener
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var album: Album

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: Album) {
        this.album = item
        itemBinding.name.text = item.title
    }

    override fun onClick(v: View?) {
        listener.onClickedAlbum(album.id)
    }
}