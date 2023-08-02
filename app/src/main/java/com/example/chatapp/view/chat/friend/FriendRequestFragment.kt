package com.example.chatapp.view.chat.friend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.databinding.FragmentFriendRequestBinding
import com.example.chatapp.model.response.UserAccountResponse
import com.example.chatapp.view.chat.friend.adapter.ItemFriendRequestAdapter
import com.example.chatapp.viewmodel.SignInViewModel
import okhttp3.internal.addHeaderLenient

class FriendRequestFragment : Fragment(), ItemFriendRequestAdapter.IItemFriendRequestAdapter {
    private lateinit var binding: FragmentFriendRequestBinding
    private var friendRequest = arrayListOf<UserAccountResponse>()
    private lateinit var viewModel: SignInViewModel
    private lateinit var adapter: ItemFriendRequestAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFriendRequestBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(SignInViewModel::class.java)
        binding.viewModel = viewModel

        adapter = ItemFriendRequestAdapter(this)
        binding.rcFriendRequest.adapter = adapter
        binding.rcFriendRequest.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.friendRequest.observe(viewLifecycleOwner, Observer {
            friendRequest = it
            adapter.notifyDataSetChanged()
        })
        return binding.root
    }

    override fun friendGetItemCount(): Int {
        return friendRequest.size
    }

    override fun friendGetItemByPosition(position: Int): UserAccountResponse {
        return friendRequest.get(position)
    }

    override fun onAddFriend(id: Int) {
        viewModel.acceptFriend(id)
        viewModel.getAllFriendRequest()
        adapter.notifyDataSetChanged()
    }

    override fun onRemoveFriend(id: Int) {
        viewModel.removeRequest(id)
        viewModel.getAllFriendRequest()
        adapter.notifyDataSetChanged()
    }
}