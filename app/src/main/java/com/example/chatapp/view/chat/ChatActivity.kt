package com.example.chatapp.view.chat

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat)

        registerClick()

    }

    private fun registerClick() {
        binding.btnChat.setOnClickListener(this)
        binding.btnFeed.setOnClickListener(this)
        binding.btnPeople.setOnClickListener(this)
        binding.btnStories.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.btnChat.id -> {
                binding.tvTitle.setText(getString(R.string.fragment_title_chat))
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(binding.fragmentContainer.id, ChatFragment())
                fragmentTransaction.commit()

            }

            binding.btnFeed.id -> {
                binding.tvTitle.setText(getString(R.string.fragment_title_feeds))
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(binding.fragmentContainer.id, FeedsFragment())
                fragmentTransaction.commit()
            }

            binding.btnPeople.id -> {
                binding.tvTitle.setText(getString(R.string.fragment_title_people))
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(binding.fragmentContainer.id, PeopleFragment())
                fragmentTransaction.commit()
            }

            binding.btnStories.id -> {
                binding.tvTitle.setText(getString(R.string.fragment_title_stories))
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(binding.fragmentContainer.id, StoriesFragment())
                fragmentTransaction.commit()
            }
        }
    }
}