package com.szaporozhets.petmvvm.ui.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.szaporozhets.petmvvm.data.entities.Album
import com.szaporozhets.petmvvm.databinding.ItemAlbumBinding

class AlbumsAdapter(private val listener: AlbumsItemListener) :
    RecyclerView.Adapter<AlbumsViewHolder>() {

    private val items = ArrayList<Album>()

    fun setItems(items: ArrayList<Album>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val binding: ItemAlbumBinding =
            ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumsViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) =
        holder.bind(items[position])
}
