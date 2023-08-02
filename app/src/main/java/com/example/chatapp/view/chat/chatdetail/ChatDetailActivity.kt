package com.example.chatapp.view.chat.chatdetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityChatDetailBinding
import com.example.chatapp.model.response.ChatMessageResponse
import com.example.chatapp.view.chat.chatdetail.adapter.ChatDetailAdapter
import com.example.chatapp.viewmodel.SignInViewModel

class ChatDetailActivity : AppCompatActivity(), ChatDetailAdapter.IChatDetailAdapter,
    View.OnClickListener {
    private lateinit var binding: ActivityChatDetailBinding
    private lateinit var viewModel: SignInViewModel
    private var listMessage = arrayListOf<ChatMessageResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_detail)
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        binding.viewModel = viewModel


        val id_send = intent.getIntExtra("id_send", 0)
        val id_receive = intent.getIntExtra("id_receive", 0)
        val receive_name = intent.getStringExtra("name_receive")

        //update viewmodel
        viewModel.setId(id_send)
        viewModel.receiveId.value = id_receive
        viewModel.receiveName.value = receive_name

        val adapter = ChatDetailAdapter(this)
        binding.rcListChat.adapter = adapter
        binding.rcListChat.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        viewModel.getChatDetailList()
        viewModel.messages.observe(this, Observer {
            listMessage = it
            adapter.notifyDataSetChanged()
        })

        binding.btnSend.setOnClickListener(this)
        binding.btnBack.setOnClickListener(this)
    }

    override fun getChatListSize(): Int {
        return listMessage.size
    }

    override fun getChatContentOnPosition(position: Int): ChatMessageResponse {
        return listMessage.get(position)
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.btnSend.id -> {
                val message = binding.edtInput.text.toString()
                binding.edtInput.text = null
                if (message.length > 0) {
                    viewModel.sendNewMessage(message)
                }
            }

            binding.btnBack.id -> {
                onBackPressed()
            }
        }
    }
}