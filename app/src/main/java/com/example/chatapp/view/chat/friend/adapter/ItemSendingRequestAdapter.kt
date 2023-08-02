package com.example.chatapp.view.chat.friend.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.databinding.ItemSendingRequestBinding
import com.example.chatapp.model.response.SendingRequestResponse

class ItemSendingRequestAdapter(val inter: IItemSendingRequestAdapter) :
    RecyclerView.Adapter<ItemSendingRequestAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemSendingRequestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SendingRequestResponse) {
            binding.sendingRequest = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemSendingRequestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return inter.friendRequestGetItemCount()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = inter.friendRequestItemByPosition(position)
        holder.bind(item)

        holder.binding.btnCancel.setOnClickListener {
            inter.onCancelRequest(position)

        }
    }


    interface IItemSendingRequestAdapter {
        fun friendRequestGetItemCount(): Int
        fun friendRequestItemByPosition(position: Int): SendingRequestResponse
        fun onCancelRequest(position: Int)
    }

}