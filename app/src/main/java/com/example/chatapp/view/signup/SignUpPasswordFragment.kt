package com.example.chatapp.view.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R

import com.example.chatapp.databinding.FragmentSignUpPasswordBinding

class SignUpPasswordFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentSignUpPasswordBinding
    private lateinit var password: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpPasswordBinding.inflate(inflater, container, false)
        binding.btnBack.setOnClickListener(this)
        binding.tvAlreadyHaveAccount.setOnClickListener(this)
        binding.tilPassword.setOnClickListener(this)
        binding.btnNext.setOnClickListener(this)

        return binding.root
    }


    override fun onClick(v: View) {
        when (v.id) {
            binding.btnBack.id -> {
                requireActivity().onBackPressed()
            }

            binding.tvAlreadyHaveAccount.id -> {
                requireActivity().finish()
            }

            binding.tilPassword.id -> {
                binding.tilPassword.error = null
            }

            binding.btnNext.id -> {
                if (checkUserInput()) {

                    moveToSignUpSuccessfulFragment()
                }
            }
        }

    }

    private fun moveToSignUpSuccessfulFragment() {
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

    private fun checkUserInput(): Boolean {
        password = binding.tietPassword.text.toString()

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


}