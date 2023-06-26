package com.example.chatapp.View.signin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.chatapp.R
import com.example.chatapp.View.signup.SignUpActivity
import com.example.chatapp.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)

        binding.btnCreateNewAccount.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}