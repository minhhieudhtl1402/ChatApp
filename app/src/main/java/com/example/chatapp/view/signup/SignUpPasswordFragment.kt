package com.example.chatapp.view.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentSignUpPasswordBinding
import com.example.chatapp.model.response.AccountResponse
import com.example.chatapp.viewmodel.SignUpViewModel

class SignUpPasswordFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentSignUpPasswordBinding
    private lateinit var password: String
    private lateinit var signUpViewModel: SignUpViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpPasswordBinding.inflate(inflater, container, false)
        signUpViewModel = ViewModelProvider(requireActivity()).get(SignUpViewModel::class.java)
        binding.signUpViewModel = signUpViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnBack.setOnClickListener(this)
        binding.tvAlreadyHaveAccount.setOnClickListener(this)
        binding.tilPassword.setOnClickListener(this)
        binding.btnNext.setOnClickListener(this)
        binding.tietPassword.doAfterTextChanged {
            updateToViewModel()
        }
        updateUI()
        return binding.root
    }

    private fun updateUI() {
        binding.tietPassword.setText(signUpViewModel.passwordLive.value)
    }

    private fun updateToViewModel() {
        signUpViewModel.setPassword(binding.tietPassword.text.toString())
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
                    signUpViewModel.signUp(
                        signUpViewModel.usernameLive.value.toString(),
                        signUpViewModel.passwordLive.value.toString()
                    )
                    signUpViewModel.signInStatusLive.observe(viewLifecycleOwner, Observer {
                        when (it) {
                            AccountResponse.SIGN_UP_SUCCESSFUL -> {
                                moveToSignUpSuccessfulFragment()
                            }
                        }
                    })
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

            binding.tilPassword.error = getString(R.string.short_password)
            return false
        }

        return true
    }


}