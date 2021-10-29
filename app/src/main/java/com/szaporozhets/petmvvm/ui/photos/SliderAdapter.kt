package com.szaporozhets.petmvvm.ui.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.szaporozhets.petmvvm.data.entities.Photo
import com.szaporozhets.petmvvm.databinding.SliderItemBinding
import com.bumptech.glide.load.model.LazyHeaders

import com.bumptech.glide.load.model.GlideUrl




class SliderAdapter : RecyclerView.Adapter<PhotoViewHolder>() {

    private var items = ArrayList<Photo>()

    fun setData(items: List<Photo>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val itemBinding =
            SliderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {

        val url = GlideUrl(
            items[position].url, LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build()
        )

        Glide.with(holder.itemView.context)
            .load(url)
            .centerCrop()
            .into(holder.binding.photo)

        holder.binding.photoTitle.text = items[position].title
    }

    override fun getItemCount(): Int = items.size
}

class PhotoViewHolder(val binding: SliderItemBinding) :
    RecyclerView.ViewHolder(binding.root)