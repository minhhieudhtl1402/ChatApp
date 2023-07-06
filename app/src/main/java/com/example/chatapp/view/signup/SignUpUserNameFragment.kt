package com.example.chatapp.view.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentSignUpUserNameBinding


class SignUpUserNameFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentSignUpUserNameBinding


    private lateinit var username: String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpUserNameBinding.inflate(inflater, container, false)
        binding.btnNext.setOnClickListener(this)
        binding.tietUsername.setOnClickListener(this)
        binding.btnBack.setOnClickListener(this)
        binding.tvAlreadyHaveAccount.setOnClickListener(this)

        return binding.root
    }


    override fun onClick(v: View) {
        when (v.id) {
            binding.btnNext.id -> {
                if (checkUserInput()) {
                    moveToSignUpPasswordFragment()
                }
            }

            binding.tietUsername.id -> {
                binding.tilUsername.error = null
            }

            binding.btnBack.id -> {
                requireActivity().onBackPressed()
            }

            binding.tvAlreadyHaveAccount.id -> {
                requireActivity().finish()
            }
        }
    }

    private fun moveToSignUpPasswordFragment() {
        val fragmentTransaction =
            requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.add(
            R.id.act_sign_up,
            SignUpBirthdayFragment(),
            SignUpBirthdayFragment::class.java.name
        )
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun checkUserInput(): Boolean {
        username = binding.tietUsername.text.toString()
        if ("".equals(username)) {
            binding.tilUsername.error = getString(R.string.user_name_empty)
            return false
        } else if (username.length < 6) {
            binding.tilUsername.error = getString(R.string.short_username)
            return false
        }
        return true
    }


}