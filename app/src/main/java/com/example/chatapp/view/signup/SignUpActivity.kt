package com.example.chatapp.view.signup

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R

import com.example.chatapp.databinding.ActivitySignUpBinding
import com.example.chatapp.viewmodel.SignUpViewModel

class SignUpActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var signUpViewModel: SignUpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        signUpViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        binding.signUpViewModel = signUpViewModel
        binding.lifecycleOwner = this


        binding.tvAlreadyHaveAccount.setOnClickListener(this)
        binding.btnGetStarted.setOnClickListener(this)
    }

    private fun moveToSignUpUserNameFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(
            R.id.act_sign_up,
            SignUpUserNameFragment(),
            SignUpUserNameFragment::class.java.name
        )
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.tvAlreadyHaveAccount.id -> {
                this.onBackPressed()
            }

            binding.btnGetStarted.id -> {
                Log.d("btnGetStarted", "on click")
                moveToSignUpUserNameFragment()
            }
        }
    }


}