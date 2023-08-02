package com.example.chatapp.view.chat.friend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentPeopleBinding
import com.example.chatapp.viewmodel.SignInViewModel

class PeopleFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentPeopleBinding
    private lateinit var viewModel: SignInViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPeopleBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(SignInViewModel::class.java)
        registerOnClick()
        viewModel.getAllSendingRequest()
        initFragment()

        return binding.root
    }

    private fun registerOnClick() {
        binding.btnAddFriend.setOnClickListener(this)
        binding.btnFriendRequest.setOnClickListener(this)
    }

    private fun initFragment() {
        val fragment = FriendRequestFragment()
        val fragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_container, fragment).commit()

    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.btnAddFriend.id -> {
                val fragment = AddFriendFragment()
                val fragmentTransaction = childFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fl_container, fragment).commit()
            }

            binding.btnFriendRequest.id -> {
                val fragment = FriendRequestFragment()
                val fragmentTransaction = childFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fl_container, fragment).commit()
            }
        }
    }


}