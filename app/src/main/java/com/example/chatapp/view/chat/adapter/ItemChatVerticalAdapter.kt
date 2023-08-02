package com.example.chatapp.view.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.databinding.ItemProfileChatVerticalBinding
import com.example.chatapp.model.response.ChatResponse

class ItemChatVerticalAdapter(
    val inter: IItemChatVerticalAdapter,
    var mList: ArrayList<ChatResponse>
) :
    RecyclerView.Adapter<ItemChatVerticalViewHolder>() {


    fun setFilteredList(mList: ArrayList<ChatResponse>) {
        this.mList = mList
        notifyDataSetChanged()
    }

    fun setChatList(mList: ArrayList<ChatResponse>) {
        this.mList = mList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemChatVerticalViewHolder {
        val binding: ItemProfileChatVerticalBinding = ItemProfileChatVerticalBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ItemChatVerticalViewHolder(binding)
    }

    override fun getItemCount(): Int {
//        return inter.iItemVerticalGetItemCount()
        return mList.size
    }


    override fun onBindViewHolder(holder: ItemChatVerticalViewHolder, position: Int) {
//        val item = inter.iItemVerticalGetItemByPosition(position)
        val item = mList.get(position)
        holder.bind(item)  //o day goi gan' du lieu vao
        holder.itemView.setOnClickListener {
            inter.iItemVerticalOnClick(position)
        }
    }

}

class ItemChatVerticalViewHolder(val binding: ItemProfileChatVerticalBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(itemChatVer: ChatResponse) {
        binding.profile = itemChatVer   //gan du lieu
    }
}


interface IItemChatVerticalAdapter {
    fun iItemVerticalGetItemCount(): Int
    fun iItemVerticalGetItemByPosition(position: Int): ChatResponse
    fun iItemVerticalOnClick(receiveId: Int)
}