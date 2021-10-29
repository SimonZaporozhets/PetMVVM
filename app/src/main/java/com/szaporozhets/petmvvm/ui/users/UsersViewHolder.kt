package com.szaporozhets.petmvvm.ui.users

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.szaporozhets.petmvvm.data.entities.User
import com.szaporozhets.petmvvm.databinding.ItemUserBinding

class UsersViewHolder(
    private val itemBinding: ItemUserBinding,
    private val listener: UsersItemListener
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var user: User

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: User) {
        this.user = item
        itemBinding.name.text = item.name
    }

    override fun onClick(v: View?) {
        listener.onClickedUser(user.id)
    }
}