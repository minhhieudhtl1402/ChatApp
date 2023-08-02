package com.example.chatapp.view.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentSignUpUserNameBinding
import com.example.chatapp.model.response.AccountResponse
import com.example.chatapp.viewmodel.SignUpViewModel


class SignUpUserNameFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentSignUpUserNameBinding
    private lateinit var username: String
    private lateinit var signUpViewModel: SignUpViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpUserNameBinding.inflate(inflater, container, false)
        signUpViewModel = ViewModelProvider(requireActivity()).get(SignUpViewModel::class.java)
        binding.signUpViewModel = signUpViewModel
        binding.lifecycleOwner = viewLifecycleOwner

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
                    if (!"".equals(binding.tietUsername.text.toString())) {
                        binding.signUpViewModel!!.setName(binding.tietUsername.text.toString())
                    }
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
        var isExist = false
        signUpViewModel.checkAccountExist(username)
        signUpViewModel.usernameStatusLive.observe(viewLifecycleOwner) {
            when (it) {
                AccountResponse.ACCOUNT_IS_EXIST -> isExist = true
                AccountResponse.ACCOUNT_IS_NOT_EXIST -> isExist = false
            }
        }

        if ("".equals(username)) {
            binding.tilUsername.error = getString(R.string.user_name_empty)
            return false
        } else if (username.length < 6) {
            binding.tilUsername.error = getString(R.string.short_username)
            return false
        }
        else if (isExist) {
            binding.tilUsername.error = getString(R.string.exist_username)
            return false
        }
        return true
    }


}