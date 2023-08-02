package com.example.chatapp.view.signin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.view.signup.SignUpActivity
import com.example.chatapp.databinding.ActivitySignInBinding
import com.example.chatapp.model.response.AccountResponse
import com.example.chatapp.view.chat.ChatActivity
import com.example.chatapp.viewmodel.SignInViewModel

class SignInActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var signInViewModel: SignInViewModel
    val bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        signInViewModel = ViewModelProvider(this).get(SignInViewModel::class.java)

        signInViewModel.statusLive.observe(this, Observer {
            when (it) {
                AccountResponse.LOGIN_SUCCESSFUL -> {

                }

                AccountResponse.INCORRECT_PASSWORD -> {
                    binding.tilPassword.error = "Incorrect password"
                }
            }
        })
        signInViewModel.idLive.observe(this, Observer {
            if(signInViewModel.idLive.value!=null){
                goToChatActivity()
            }
        })


        binding.btnLogin.setOnClickListener(this)
        binding.btnCreateNewAccount.setOnClickListener(this)

    }

    private fun goToChatActivity() {
        val intent = Intent()
        intent.setClass(this, ChatActivity::class.java)
        intent.putExtra("id", signInViewModel.idLive.value)
        intent.putExtra("name", signInViewModel.usernameLive.value)
        intent.putExtra("name", signInViewModel.passwordLive.value)
        startActivity(intent)
        finish()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnLogin.id -> {
                signInViewModel.signIn(
                    binding.tietUsername.text.toString(),
                    binding.tietPassword.text.toString()
                )

            }

            binding.tilPassword.id -> {
                binding.tilPassword.error = null
            }

            binding.btnCreateNewAccount.id -> {
                val intent = Intent()
                intent.setClass(this, SignUpActivity::class.java)
                startActivity(intent)
            }
        }
    }
}