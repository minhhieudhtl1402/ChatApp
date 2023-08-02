package com.example.chatapp.view.chat.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.chatapp.view.chat.chatdetail.ChatFragment
import com.example.chatapp.view.chat.feed.FeedsFragment
import com.example.chatapp.view.chat.friend.PeopleFragment
import com.example.chatapp.view.chat.story.StoriesFragment

class ChatViewPagerAdapter(fragment: FragmentManager) : FragmentStatePagerAdapter(fragment) {
    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ChatFragment()
            1 -> FeedsFragment()
            2 -> PeopleFragment()
            3 -> StoriesFragment()
            else -> ChatFragment()
        }
    }
}