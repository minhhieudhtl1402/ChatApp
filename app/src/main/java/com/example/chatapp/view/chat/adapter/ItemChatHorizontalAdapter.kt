package com.example.chatapp.view.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.databinding.ItemProfileChatHorizontalBinding
import com.example.chatapp.model.response.ChatResponse

class ItemChatHorizontalAdapter(val inter: IItemChatVerticalAdapter) :
    RecyclerView.Adapter<ItemChatHorizontalAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemProfileChatHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemChatVer: ChatResponse) {
            binding.profile = itemChatVer
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProfileChatHorizontalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return inter.iItemVerticalGetItemCount()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemChatHor = inter.iItemVerticalGetItemByPosition(position)
        holder.bind(itemChatHor)
        holder.itemView.setOnClickListener {
            inter.iItemVerticalOnClick(position)
        }
    }

}
