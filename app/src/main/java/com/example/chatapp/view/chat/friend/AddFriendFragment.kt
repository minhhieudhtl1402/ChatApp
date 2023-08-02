package com.example.chatapp.view.chat.friend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.databinding.FragmentAddFriendBinding
import com.example.chatapp.model.response.FriendRequestResponse
import com.example.chatapp.model.response.SendingRequestResponse
import com.example.chatapp.view.chat.friend.adapter.ItemSendingRequestAdapter
import com.example.chatapp.viewmodel.SignInViewModel

class AddFriendFragment : Fragment(), View.OnClickListener,
    ItemSendingRequestAdapter.IItemSendingRequestAdapter {
    private lateinit var binding: FragmentAddFriendBinding
    private lateinit var viewModel: SignInViewModel
    private var sendingRequest = arrayListOf<SendingRequestResponse>()
    private lateinit var adapter: ItemSendingRequestAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddFriendBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(SignInViewModel::class.java)
        registerOnClick()
        viewModel.friendResponse.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                FriendRequestResponse.SEND_REQUEST_FAILED -> {
                    binding.tilInput.error = "Username is not exist or you have already added"
                }

                FriendRequestResponse.SEND_REQUEST_SUCCESSFUL -> {
                    Toast.makeText(requireContext(), "Send request successful", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
        viewModel.getAllSendingRequest()
        viewModel.sendingRequestResponse.observe(viewLifecycleOwner, Observer {
            sendingRequest = it
            adapter.notifyDataSetChanged()
        })
        adapter = ItemSendingRequestAdapter(this)
        binding.rcSendingRequest.adapter = adapter
        binding.rcSendingRequest.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        return binding.root
    }

    private fun registerOnClick() {
        binding.btnRequest.setOnClickListener(this)
        binding.tilInput.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.btnRequest.id -> {
                val name = binding.tietUsername.text.toString()
                viewModel.requestNewFriend(viewModel.idLive.value!!, name)
                adapter.notifyDataSetChanged()
            }

            binding.tilInput.id -> {
                binding.tilInput.error = null
            }
        }
    }

    override fun friendRequestGetItemCount(): Int {
        return sendingRequest.size
    }

    override fun friendRequestItemByPosition(position: Int): SendingRequestResponse {
        return sendingRequest.get(position)
    }

    override fun onCancelRequest(position: Int) {
        viewModel.cancelSendingRequest(sendingRequest.get(position).id)
        viewModel.getAllSendingRequest()
        adapter.notifyDataSetChanged()
    }
}