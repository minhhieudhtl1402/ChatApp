package com.example.chatapp.view.signin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.view.signup.SignUpActivity
import com.example.chatapp.databinding.ActivitySignInBinding
import com.example.chatapp.view.chat.ChatActivity

class SignInActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)

        binding.btnLogin.setOnClickListener(this)
        binding.btnCreateNewAccount.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnLogin.id -> {
                val intent = Intent()
                intent.setClass(this, ChatActivity::class.java)
                startActivity(intent)
            }

            binding.btnCreateNewAccount.id -> {
                val intent = Intent()
                intent.setClass(this, SignUpActivity::class.java)
                startActivity(intent)
            }
        }
    }
}