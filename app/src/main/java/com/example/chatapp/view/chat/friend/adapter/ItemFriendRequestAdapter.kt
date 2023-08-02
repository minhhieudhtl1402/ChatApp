package com.example.chatapp.view.chat.friend.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.databinding.ItemFriendRequestBinding
import com.example.chatapp.model.response.UserAccountResponse

class ItemFriendRequestAdapter(val inter: IItemFriendRequestAdapter) :
    RecyclerView.Adapter<ItemFriendRequestAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemFriendRequestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserAccountResponse) {
            binding.profile = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemFriendRequestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return inter.friendGetItemCount()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = inter.friendGetItemByPosition(position)
        holder.bind(item)
        holder.binding.btnAddFriend.setOnClickListener {
            inter.onAddFriend(inter.friendGetItemByPosition(position).id)
        }
        holder.binding.btnRemove.setOnClickListener {
            inter.onRemoveFriend(inter.friendGetItemByPosition(position).id)
        }
    }


    interface IItemFriendRequestAdapter {
        fun friendGetItemCount(): Int
        fun friendGetItemByPosition(position: Int): UserAccountResponse
        fun onAddFriend(id: Int)
        fun onRemoveFriend(id: Int)
    }

}