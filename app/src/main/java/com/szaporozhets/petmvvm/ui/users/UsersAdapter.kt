package com.szaporozhets.petmvvm.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.szaporozhets.petmvvm.data.entities.User
import com.szaporozhets.petmvvm.databinding.ItemUserBinding

class UsersAdapter(private val listener: UsersItemListener) :
    RecyclerView.Adapter<UsersViewHolder>() {

    private val items = ArrayList<User>()

    fun setItems(items: ArrayList<User>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val binding: ItemUserBinding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) =
        holder.bind(items[position])
}

