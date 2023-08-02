package com.example.chatapp.view.chat.chatdetail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.databinding.FragmentChatBinding
import com.example.chatapp.model.response.ChatResponse
import com.example.chatapp.view.chat.adapter.IItemChatVerticalAdapter
import com.example.chatapp.view.chat.adapter.ItemChatHorizontalAdapter
import com.example.chatapp.view.chat.adapter.ItemChatVerticalAdapter
import com.example.chatapp.viewmodel.SignInViewModel
import java.util.Locale

class ChatFragment : Fragment(), IItemChatVerticalAdapter {
    private lateinit var binding: FragmentChatBinding
    private lateinit var viewModel: SignInViewModel
    private lateinit var verAdapter: ItemChatVerticalAdapter
    private lateinit var horAdapter: ItemChatHorizontalAdapter
    var chatList: ArrayList<ChatResponse> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(SignInViewModel::class.java)
        verAdapter = ItemChatVerticalAdapter(this, chatList)
        horAdapter = ItemChatHorizontalAdapter(this)
        binding.rcChat.adapter = verAdapter
        binding.rcChat.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.rcOnline.adapter = horAdapter
        binding.rcOnline.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        viewModel.chatListLive.observe(viewLifecycleOwner, Observer {
            chatList = it
            verAdapter.setChatList(it)
            verAdapter.notifyDataSetChanged()
            horAdapter.notifyDataSetChanged()
        })

        binding.svChat.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }


        })


        return binding.root
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filterList = ArrayList<ChatResponse>()
            for (i in chatList) {
                if (i.name.lowercase(Locale.ROOT).contains(query)) {
                    filterList.add(i)
                }
            }
            if (filterList.isEmpty()) {

            } else {
                verAdapter.setFilteredList(filterList)
            }
        }
    }


    override fun iItemVerticalGetItemCount(): Int {
        return chatList.size
    }

    override fun iItemVerticalGetItemByPosition(position: Int): ChatResponse {
        return chatList.get(position)
    }

    override fun iItemVerticalOnClick(position: Int) {

        val intent = Intent(requireContext(), ChatDetailActivity::class.java)
        intent.putExtra("id_send", viewModel.idLive.value)
        intent.putExtra("id_receive", chatList.get(position).receiveId)
        intent.putExtra("name_receive", chatList.get(position).name)
        startActivity(intent)
    }


}