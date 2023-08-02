package com.example.chatapp.view.chat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityChatBinding
import com.example.chatapp.model.response.ChatResponse
import com.example.chatapp.view.chat.adapter.ChatViewPagerAdapter
import com.example.chatapp.view.signin.SignInActivity
import com.example.chatapp.viewmodel.SignInViewModel
import com.google.android.material.navigation.NavigationView

class ChatActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityChatBinding
    private lateinit var signInViewModel: SignInViewModel
    private val DEFAULT_BACKPRESS_DURATION = 2000
    private var lastTimeBackPressed: Long = 0L

    private var chats: ArrayList<ChatResponse> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat)
        registerClick()

        signInViewModel =
            ViewModelProvider(this).get(SignInViewModel::class.java)
        binding.vpChat.adapter = ChatViewPagerAdapter(supportFragmentManager)

        val userId = intent.getIntExtra("id", 0)
        signInViewModel.setId(userId)
        Log.d("userId", userId.toString())
        signInViewModel.getChatList()
        signInViewModel.getAllFriendRequest()

        binding.nvChat.setNavigationItemSelectedListener(OnNavigationItemSelectImpl())
        //handle scroll and click between fragment
        binding.vpChat.addOnPageChangeListener(OnPageChangeListenerImpl())
        binding.bnvBottom.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.page_chat -> {
                    binding.vpChat.setCurrentItem(0)
                    true
                }

                R.id.page_feeds -> {
                    binding.vpChat.setCurrentItem(1)
                    true
                }

                R.id.page_people -> {
                    binding.vpChat.setCurrentItem(2)
                    true
                }

                R.id.page_stories -> {
                    binding.vpChat.setCurrentItem(3)
                    true
                }

                else -> {
                    true
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        signInViewModel.getChatList()
    }

    private fun registerClick() {
        binding.dlChat.setOnClickListener(this)
        binding.btnMenu.setOnClickListener(this)
    }

    override fun onBackPressed() {
        if (lastTimeBackPressed + DEFAULT_BACKPRESS_DURATION < System.currentTimeMillis()) {
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show()
        } else {
            super.onBackPressed()
            finish()
        }
        lastTimeBackPressed = System.currentTimeMillis()

    }

    inner class OnPageChangeListenerImpl : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {

        }

        override fun onPageSelected(position: Int) {
            when (position) {
                0 -> binding.bnvBottom.menu.findItem(R.id.page_chat).setChecked(true)
                1 -> binding.bnvBottom.menu.findItem(R.id.page_feeds).setChecked(true)
                2 -> binding.bnvBottom.menu.findItem(R.id.page_people).setChecked(true)
                3 -> binding.bnvBottom.menu.findItem(R.id.page_stories).setChecked(true)
            }
        }

        override fun onPageScrollStateChanged(state: Int) {

        }

    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.dlChat.id -> {

            }

            binding.btnMenu.id -> {
                binding.dlChat.openDrawer(GravityCompat.START)
            }
        }
    }


    private fun changeCurrentFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.vpChat.id, fragment, fragment::class.java.name)
        fragmentTransaction.commit()
    }

    inner class OnNavigationItemSelectImpl : NavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.page_chat -> {
//                changeCurrentFragment(ChatFragment())
                    binding.vpChat.setCurrentItem(0)
                    true
                }

                R.id.page_feeds -> {
                    binding.vpChat.setCurrentItem(1)
                    binding.dlChat.closeDrawer(GravityCompat.START)

                    true
                }

                R.id.page_people -> {
                    binding.vpChat.setCurrentItem(2)
                    binding.dlChat.closeDrawer(GravityCompat.START)

                    true
                }

                R.id.page_stories -> {
                    binding.vpChat.setCurrentItem(3)
                    binding.dlChat.closeDrawer(GravityCompat.START)
                    true
                }

                R.id.logout -> {
                    finish()
                    val intent = Intent(this@ChatActivity, SignInActivity::class.java)
                    startActivity(intent)
                    true
                }

                else -> {
                    true
                }
            }
        }
    }

}