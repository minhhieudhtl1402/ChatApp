package com.example.chatapp.view.chat.chatdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.databinding.ItemChatReceiveBinding
import com.example.chatapp.databinding.ItemChatSendBinding
import com.example.chatapp.model.response.ChatMessageResponse

class ChatDetailAdapter(val inter: IChatDetailAdapter) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class SendViewHolder(val binding: ItemChatSendBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemMessage: ChatMessageResponse) {
            binding.chat = itemMessage
        }
    }

    class ReceiveViewHolder(val binding: ItemChatReceiveBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemMessage: ChatMessageResponse) {
            binding.chat = itemMessage
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ChatMessageResponse.TYPE_SEND -> {
                val binding = ItemChatSendBinding.inflate(inflater, parent, false)
                return SendViewHolder(binding)
            }

            ChatMessageResponse.TYPE_RECEIVE -> {
                val binding = ItemChatReceiveBinding.inflate(inflater, parent, false)
                return ReceiveViewHolder(binding)
            }

            else -> {
                val binding = ItemChatSendBinding.inflate(inflater, parent, false)
                return SendViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return inter.getChatListSize()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SendViewHolder -> {
                holder.bind(inter.getChatContentOnPosition(position))
            }

            is ReceiveViewHolder -> {
                holder.bind(inter.getChatContentOnPosition(position))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return inter.getChatContentOnPosition(position).type
    }

    interface IChatDetailAdapter {
        fun getChatListSize(): Int
        fun getChatContentOnPosition(position: Int): ChatMessageResponse
    }
}