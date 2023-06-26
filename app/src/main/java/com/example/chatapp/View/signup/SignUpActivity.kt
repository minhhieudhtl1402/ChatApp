package com.example.chatapp.View.signup

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        binding.tvAlreadyHaveAccount.setOnClickListener {
            this.onBackPressed()
        }
        binding.btnGetStarted.setOnClickListener {
            Log.d("btnGetStarted", "on click")
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(
                R.id.act_sign_up,
                SignUpNameFragment(),
                SignUpNameFragment::class.java.name
            )
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

        }
    }
}