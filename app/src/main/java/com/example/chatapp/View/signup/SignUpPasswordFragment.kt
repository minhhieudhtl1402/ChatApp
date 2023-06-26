package com.example.chatapp.View.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentSignUpMobileNumberBinding
import com.example.chatapp.databinding.FragmentSignUpPasswordBinding
import com.example.chatapp.databinding.FragmentSignUpSuccessfulBinding
import com.google.android.material.textfield.TextInputLayout

class SignUpPasswordFragment : Fragment() {
    private lateinit var binding: FragmentSignUpPasswordBinding
    private fun checkUserInput(): Boolean {
        val password = binding.tietPassword.text.toString()
        if (password.equals("")) {
            binding.tilPassword.error = getString(R.string.empty_password)
            return false
        } else if (password.length < 6) {
            //6 ->chuyen thanh static final
            binding.tilPassword.error = getString(R.string.short_password)
            return false
        }

        return true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpPasswordBinding.inflate(inflater, container, false)
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.tvAlreadyHaveAccount.setOnClickListener {
            requireActivity().finish()
        }
        binding.tilPassword.setOnClickListener {
            (it as TextInputLayout).error = null
        }
        binding.btnNext.setOnClickListener {
            if (checkUserInput()) {
                val fragmentTransaction =
                    requireActivity().supportFragmentManager.beginTransaction()
                fragmentTransaction.add(
                    R.id.act_sign_up,
                    SignUpSuccessfulFragment(),
                    SignUpSuccessfulFragment::class.java.name
                )
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        }


        return binding.root
    }
}